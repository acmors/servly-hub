package schedulegenerator.acmorshub.payment.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import schedulegenerator.acmorshub.payment.dto.CreatePayment;
import schedulegenerator.acmorshub.payment.dto.ResponsePayment;
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
}
