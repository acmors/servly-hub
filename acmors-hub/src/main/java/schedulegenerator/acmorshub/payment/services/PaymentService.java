package schedulegenerator.acmorshub.payment.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import schedulegenerator.acmorshub.payment.dto.CreatePayment;
import schedulegenerator.acmorshub.payment.dto.PaymentMapper;
import schedulegenerator.acmorshub.payment.dto.ResponsePayment;
import schedulegenerator.acmorshub.payment.entities.Payment;
import schedulegenerator.acmorshub.payment.entities.enums.PaymentStatus;
import schedulegenerator.acmorshub.payment.integration.pix.PixChargeRequest;
import schedulegenerator.acmorshub.payment.integration.pix.PixChargeResponse;
import schedulegenerator.acmorshub.payment.integration.pix.PixClient;
import schedulegenerator.acmorshub.payment.repository.PaymentRepository;
import schedulegenerator.acmorshub.product.entities.ServiceOrder;
import schedulegenerator.acmorshub.product.service.ServiceOrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ServiceOrderService serviceOrderService;
    private final PixClient pixClient;

    public ResponsePayment createPayment(CreatePayment createPayment) {
        //Busca ordem de pagamento
        ServiceOrder serviceOrder =serviceOrderService.findById(createPayment.getServiceOrderId());

        Payment payment = new Payment();
        payment.setServiceOrder(serviceOrder);
        payment.setPaymentMethod(createPayment.getMethod());
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setCreateAt(LocalDateTime.now());
        payment.setAmount(serviceOrder.getTotalPrice());

        PixChargeRequest request = new PixChargeRequest(
                new PixChargeRequest.Amount(
                        serviceOrder
                                .getTotalPrice()
                                .setScale(2)
                                .toPlainString()
                        ), "dev@example.com"
        );

        PixChargeResponse pixChargeResponse = pixClient.charge(request);
        payment.setProviderTransactionId(pixChargeResponse.getTxid());
        payment.setPixCopyPaste(pixChargeResponse.getPixCopiaECola());

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
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException("Payment not found"));

        return PaymentMapper.toDTO(payment);
    }
}
