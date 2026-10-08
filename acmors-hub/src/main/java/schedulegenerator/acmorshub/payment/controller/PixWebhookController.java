package schedulegenerator.acmorshub.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import schedulegenerator.acmorshub.payment.integration.pix.PixWebhookRequest;
import schedulegenerator.acmorshub.payment.services.PaymentService;
import schedulegenerator.acmorshub.payment.utils.PixWebhookSignatureValidator;
import tools.jackson.databind.ObjectMapper;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/webhooks")
@RequiredArgsConstructor
public class PixWebhookController {

    private final PaymentService paymentService;
    private final PixWebhookSignatureValidator signatureValidator;
    private final ObjectMapper objectMapper;

    @PostMapping("/pix")
    public ResponseEntity<Void> receivePixWebhook(@RequestBody String rawBody, @RequestHeader(value = "X-Signature") String signature) throws NoSuchAlgorithmException {
        System.out.println(">>> WEBHOOK PIX RECEBIDO <<<");
        if(!signatureValidator.validateSignature(rawBody, signature)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        final PixWebhookRequest webhook;

        try{
            webhook = objectMapper.readValue(rawBody, PixWebhookRequest.class);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (webhook.getPix() == null || webhook.getPix().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        for(PixWebhookRequest.PixReceived pix : webhook.getPix()){
            if (pix.getTxid() == null || pix.getValor() == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            paymentService.confirmPayment(pix.getTxid(), pix.getValor());
        }

        return ResponseEntity.noContent().build();
    }

}
