package schedulegenerator.acmorshub.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import schedulegenerator.acmorshub.product.dto.product.CreateProduct;
import schedulegenerator.acmorshub.product.dto.product.ResponseProduct;
import schedulegenerator.acmorshub.product.dto.product.UpdateProduct;
import schedulegenerator.acmorshub.product.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseProduct> create(@RequestBody CreateProduct createProduct){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(createProduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseProduct> update(@PathVariable Long id, @RequestBody UpdateProduct updateProduct){
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(id, updateProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseProduct> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.delete(id));
    }
}
