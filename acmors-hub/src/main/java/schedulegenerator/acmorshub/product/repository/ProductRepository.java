package schedulegenerator.acmorshub.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schedulegenerator.acmorshub.product.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
