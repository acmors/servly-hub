package schedulegenerator.acmorshub.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import schedulegenerator.acmorshub.product.dto.CreateServiceOrder;
import schedulegenerator.acmorshub.product.dto.ServiceOrderResponse;
import schedulegenerator.acmorshub.product.service.ServiceOrderService;

@RestController
@RequestMapping("/api/service-order")
@RequiredArgsConstructor
public class ServiceOrderController {

    private final ServiceOrderService serviceOrderService;

    @PostMapping
    public ResponseEntity<ServiceOrderResponse> createOrder(@RequestBody CreateServiceOrder createServiceOrder) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceOrderService.createOrder(createServiceOrder));
    }
}
