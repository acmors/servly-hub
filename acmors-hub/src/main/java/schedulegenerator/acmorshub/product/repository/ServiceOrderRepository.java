package schedulegenerator.acmorshub.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schedulegenerator.acmorshub.product.entities.ServiceOrder;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {
}
