package schedulegenerator.acmorshub.payment.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import schedulegenerator.acmorshub.payment.dto.CreatePayment;
import schedulegenerator.acmorshub.payment.dto.ResponsePayment;
import schedulegenerator.acmorshub.payment.entities.Payment;
import schedulegenerator.acmorshub.payment.services.PaymentService;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<ResponsePayment> createPayment(@RequestBody CreatePayment createPayment){
        return ResponseEntity.ok(paymentService.createPayment(createPayment));
    }

    @PostMapping("/{paymentId}/pay")
    public ResponseEntity<ResponsePayment> payPayment(@PathVariable Long paymentId){
        return ResponseEntity.ok(paymentService.pay(paymentId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePayment> getPayment(@PathVariable Long id){
        return ResponseEntity.ok(paymentService.getPayment(id));
    }
}
