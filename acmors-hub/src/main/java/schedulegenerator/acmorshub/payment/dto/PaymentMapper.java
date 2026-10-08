package schedulegenerator.acmorshub.payment.dto;

import org.springframework.stereotype.Component;
import schedulegenerator.acmorshub.payment.entities.Payment;

@Component
public class PaymentMapper {

    public static ResponsePayment toDTO(Payment payment) {
        return new ResponsePayment(
                payment.getPaymentMethod(),
                payment.getPaymentStatus(),
                payment.getAmount(),
                payment.getCreateAt(),
                payment.getPaidAt()
        );
    }
}
