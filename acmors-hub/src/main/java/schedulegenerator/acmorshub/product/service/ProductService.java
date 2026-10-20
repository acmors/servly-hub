package schedulegenerator.acmorshub.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedulegenerator.acmorshub.exceptions.ResourceNotFoundExeption;
import schedulegenerator.acmorshub.product.dto.product.CreateProduct;
import schedulegenerator.acmorshub.product.dto.product.ProductMapper;
import schedulegenerator.acmorshub.product.dto.product.ResponseProduct;
import schedulegenerator.acmorshub.product.dto.product.UpdateProduct;
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

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return  productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("Product not found"));
    }

    @Transactional
    public ResponseProduct update(Long id, UpdateProduct updateProduct) {
        Product product = findById(id);
        product.setName(updateProduct.getName());
        product.setPrice(updateProduct.getPrice());
        product.setDescription(updateProduct.getDescription());

        productRepository.save(product);
        return ProductMapper.toDto(product);
    }

    @Transactional
    public ResponseProduct delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
        return ProductMapper.toDto(product);
    }
}
