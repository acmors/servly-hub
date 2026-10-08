package schedulegenerator.acmorshub.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import schedulegenerator.acmorshub.payment.entities.enums.PaymentMethod;
import schedulegenerator.acmorshub.payment.entities.enums.PaymentStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePayment {

    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private LocalDateTime paidAt;
}
