package schedulegenerator.acmorshub.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schedulegenerator.acmorshub.payment.entities.Payment;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByServiceOrderId(Long serviceOrderId);

    Optional<Payment> findByProviderTransactionId(String providerTransactionId);
}
