package schedulegenerator.acmorshub.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schedulegenerator.acmorshub.product.entities.ServiceOrder;
import java.util.List;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

    List<ServiceOrder> findServiceOrderCompanyById(Long companyId);
    List<ServiceOrder> findServiceOrderByCustomerId(Long customerId);

}
