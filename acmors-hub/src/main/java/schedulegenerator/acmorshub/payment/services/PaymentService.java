package schedulegenerator.acmorshub.payment.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import schedulegenerator.acmorshub.exceptions.ResourceNotFoundExeption;
import schedulegenerator.acmorshub.payment.dto.CreatePayment;
import schedulegenerator.acmorshub.payment.dto.PaymentMapper;
import schedulegenerator.acmorshub.payment.dto.ResponsePayment;
import schedulegenerator.acmorshub.payment.entities.Payment;
import schedulegenerator.acmorshub.payment.entities.enums.PaymentStatus;
import schedulegenerator.acmorshub.payment.integration.asaas.AsaasCustomerService;
import schedulegenerator.acmorshub.payment.repository.PaymentRepository;
import schedulegenerator.acmorshub.product.entities.ServiceOrder;
import schedulegenerator.acmorshub.product.service.ServiceOrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ServiceOrderService serviceOrderService;
    private final AsaasCustomerService asaasCustomerService;
    private final PixClient pixClient;
    private final RestClient restClient;

    public PaymentService(PaymentRepository paymentRepository, ServiceOrderService serviceOrderService, AsaasCustomerService asaasCustomerService, PixClient pixClient, @Qualifier("asaasRestClient") RestClient restClient) {
        this.paymentRepository = paymentRepository;
        this.serviceOrderService = serviceOrderService;
        this.asaasCustomerService = asaasCustomerService;
        this.pixClient = pixClient;
        this.restClient = restClient;
    }

    public ResponsePayment createPayment(CreatePayment createPayment) {
        //Busca ordem de pagamento
        ServiceOrder serviceOrder =serviceOrderService.findById(createPayment.getServiceOrderId());

        //Busca ou cria cliente no Asaas
        String asaasCustomId = asaasCustomerService.getOrCreateAsaasCustomer(serviceOrder.getCustomer());


        //função que monta os dados que serão enviados para o Asaas
        Map<String, Object> request = Map.of(
                "customer", asaasCustomId,
                "billingType", "PIX",
                "value", serviceOrder.getTotalPrice(),
                "dueDate", LocalDateTime.now().plusDays(1).toString()
        );

        //função que vai enviar a cobrança para o Asaas
        Map<?,?> response = restClient.post()
                .uri("/payments")
                .body(request)
                .retrieve()
                .body(Map.class);

        //Valida o retorno do response
        if(response == null || response.get("id").toString() == null){
            throw new IllegalArgumentException("O Asaas não retornou o identificador da cobrança");
        }
        String asaasPaymentId = response.get("id").toString();

        //Busca QRCode e Copia e Cola
        Map<?,?> pixResponse = restClient.get()
                .uri("/payments/{id}/pixQrCode", asaasPaymentId)
                .retrieve()
                .body(Map.class);

        Payment payment = new Payment();
        payment.setServiceOrder(serviceOrder);
        payment.setPaymentMethod(createPayment.getMethod());
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setCreateAt(LocalDateTime.now());
        payment.setAmount(serviceOrder.getTotalPrice());
        payment.setProviderTransactionId(asaasPaymentId);

        //Se o PixResponse nao foi null, vamos ter o Copia e Cola e o QrCode
        if (pixResponse != null && pixResponse.get("payload") != null){
            payment.setPixCopyPaste(pixResponse.get("payload").toString());
        }

        Payment create =  paymentRepository.save(payment);
        return PaymentMapper.toDTO(create);
    }

    public ResponsePayment pay(Long paymentId) {
         Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException("Payment not found"));
         pixClient.pay(payment.getProviderTransactionId());
         return PaymentMapper.toDTO(payment);
    }

    public void confirmPayment(String txid, String receivedAmount) {
        Payment payment = paymentRepository.findByProviderTransactionId(txid)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        BigDecimal amount = new BigDecimal(receivedAmount);

        if (payment.getAmount().compareTo(amount) != 0) {
            throw new RuntimeException("Valor recebido não é igual ao pagamento");
        }

        if (payment.getPaymentStatus().equals(PaymentStatus.PAID)) {
            return;
        }

        payment.setPaymentStatus(PaymentStatus.PAID);
        payment.setPaidAt(LocalDateTime.now());

        paymentRepository.save(payment);
    }

    public ResponsePayment getPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new ResourceNotFoundExeption("Payment not found"));

        return PaymentMapper.toDTO(payment);
    }
}
