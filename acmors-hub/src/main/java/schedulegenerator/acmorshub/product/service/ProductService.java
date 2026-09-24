package schedulegenerator.acmorshub.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedulegenerator.acmorshub.product.dto.CreateProduct;
import schedulegenerator.acmorshub.product.dto.ProductMapper;
import schedulegenerator.acmorshub.product.dto.ResponseProduct;
import schedulegenerator.acmorshub.product.entities.Product;
import schedulegenerator.acmorshub.product.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ResponseProduct create(CreateProduct createProduct) {
        Product product = new Product();
        product.setName(createProduct.getName());
        product.setPrice(createProduct.getPrice());
        product.setDescription(createProduct.getDescription());
        productRepository.save(product);

        return ProductMapper.toDto(product);
    }
}
