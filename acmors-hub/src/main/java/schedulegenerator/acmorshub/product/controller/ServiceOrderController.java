package schedulegenerator.acmorshub.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import schedulegenerator.acmorshub.product.dto.service_order.CreateServiceOrder;
import schedulegenerator.acmorshub.product.dto.service_order.ServiceOrderResponse;
import schedulegenerator.acmorshub.product.service.ServiceOrderService;

import java.util.List;

@RestController
@RequestMapping("/api/service-order")
@RequiredArgsConstructor
public class ServiceOrderController {

    private final ServiceOrderService serviceOrderService;

    @PostMapping
    public ResponseEntity<ServiceOrderResponse> createOrder(@RequestBody CreateServiceOrder createServiceOrder) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceOrderService.createOrder(createServiceOrder));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOrderResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(serviceOrderService.findByIdDto(id));
    }

    @GetMapping("/{customerId")
    public ResponseEntity<List<ServiceOrderResponse>> findByCustomerId(@PathVariable Long customerId){
        return ResponseEntity.ok(serviceOrderService.findServiceOrderByCustomerId(customerId));
    }

    @GetMapping("/{companyId")
    public ResponseEntity<List<ServiceOrderResponse>> findByCompanyId(@PathVariable Long companyId){
        return ResponseEntity.ok(serviceOrderService.findServiceOrderByCompanyId(companyId));
    }
}
