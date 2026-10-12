package schedulegenerator.acmorshub.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schedulegenerator.acmorshub.customer.entities.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByAsaasCustomerId(String asaasCustomerId);
}
