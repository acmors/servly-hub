package schedulegenerator.acmorshub.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schedulegenerator.acmorshub.payment.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
