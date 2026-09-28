package schedulegenerator.acmorshub.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schedulegenerator.acmorshub.customer.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
