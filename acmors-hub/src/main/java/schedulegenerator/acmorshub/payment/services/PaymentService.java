package schedulegenerator.acmorshub.payment.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import schedulegenerator.acmorshub.payment.dto.CreatePayment;
import schedulegenerator.acmorshub.payment.dto.PaymentMapper;
import schedulegenerator.acmorshub.payment.dto.ResponsePayment;
import schedulegenerator.acmorshub.payment.entities.Payment;
import schedulegenerator.acmorshub.payment.entities.enums.PaymentStatus;
import schedulegenerator.acmorshub.payment.repository.PaymentRepository;
import schedulegenerator.acmorshub.product.entities.ServiceOrder;
import schedulegenerator.acmorshub.product.service.ServiceOrderService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ServiceOrderService serviceOrderService;

    public ResponsePayment createPayment(CreatePayment createPayment) {
        //Busca ordem de pagamento
        ServiceOrder serviceOrder =serviceOrderService.findById(createPayment.getServiceOrderId());

        Payment payment = new Payment();

        payment.setServiceOrder(serviceOrder);
        payment.setPaymentMethod(createPayment.getMethod());
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setCreateAt(LocalDateTime.now());
        payment.setAmount(serviceOrder.getTotalPrice());

        Payment create =  paymentRepository.save(payment);
        return PaymentMapper.toDTO(create);
    }
}
