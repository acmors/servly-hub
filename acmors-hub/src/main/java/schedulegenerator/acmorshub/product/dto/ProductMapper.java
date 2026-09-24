package schedulegenerator.acmorshub.product.dto;

import org.springframework.stereotype.Component;
import schedulegenerator.acmorshub.product.entities.Product;

@Component
public class ProductMapper {

    public static ResponseProduct toDto(Product product) {
        return new ResponseProduct(
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}
