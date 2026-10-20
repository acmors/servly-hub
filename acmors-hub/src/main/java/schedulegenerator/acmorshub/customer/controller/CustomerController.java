package schedulegenerator.acmorshub.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import schedulegenerator.acmorshub.customer.dto.CreateCustomer;
import schedulegenerator.acmorshub.customer.dto.ResponseCustomer;
import schedulegenerator.acmorshub.customer.dto.UpdateCustomer;
import schedulegenerator.acmorshub.customer.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<ResponseCustomer> createCustomer(@RequestBody CreateCustomer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCustomer> updateCustomer( @PathVariable Long id,@RequestBody UpdateCustomer customer) {
        return ResponseEntity.ok().body(customerService.update(id, customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseCustomer> deleteCustomer(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(customerService.delete(id));
    }
}
