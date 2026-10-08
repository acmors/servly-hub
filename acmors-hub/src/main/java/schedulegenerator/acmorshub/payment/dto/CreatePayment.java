package schedulegenerator.acmorshub.payment.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import schedulegenerator.acmorshub.payment.entities.enums.PaymentMethod;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePayment {

    private Long serviceOrderId;
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;
}
