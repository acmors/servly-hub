package schedulegenerator.acmorshub.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import schedulegenerator.acmorshub.product.dto.CreateProduct;
import schedulegenerator.acmorshub.product.dto.ResponseProduct;
import schedulegenerator.acmorshub.product.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
public ResponseEntity<ResponseProduct> create(CreateProduct createProduct){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(createProduct));
    }
}
