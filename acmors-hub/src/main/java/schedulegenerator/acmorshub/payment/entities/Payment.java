package schedulegenerator.acmorshub.payment.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import schedulegenerator.acmorshub.payment.entities.enums.PaymentMethod;
import schedulegenerator.acmorshub.payment.entities.enums.PaymentStatus;
import schedulegenerator.acmorshub.product.entities.ServiceOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_order_id", nullable = false)
    private ServiceOrder serviceOrder;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private BigDecimal amount;
    private String providerTransactionId;

    @Column(length = 2000)
    private String pixCopyPaste;

    private LocalDateTime createAt;
    private LocalDateTime paidAt;
}
