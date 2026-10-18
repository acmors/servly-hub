package schedulegenerator.acmorshub.payment.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import schedulegenerator.acmorshub.payment.services.PaymentService;
import tools.jackson.databind.JsonNode;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@RestController
@RequestMapping("/api/webhooks/asaas")
public class AsaasWebhookController {
    private final PaymentService paymentService;
    private final String webhookToken;

    public AsaasWebhookController(
            PaymentService paymentService,
            @Value("${asaas.webhook.token}") String webhookToken) {
        this.paymentService = paymentService;
        this.webhookToken = webhookToken;
    }

    @PostMapping
    public ResponseEntity<Void> receiveWebhook(
            @RequestHeader(
                    value = "asaas-access-token",
                    required = false
            ) String receivedToken,
            @RequestBody JsonNode payload) {

        if (receivedToken == null ||
                !MessageDigest.isEqual(
                        webhookToken.getBytes(StandardCharsets.UTF_8),
                        receivedToken.getBytes(StandardCharsets.UTF_8))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String event = payload.path("event").asText();
        JsonNode payment = payload.path("payment");

        if ("PAYMENT_RECEIVED".equals(event)) {
            String asaasPaymentId = payment.path("id").asText();
            String amount = payment.path("value").asText();

            if (asaasPaymentId.isBlank() || amount.isBlank()) {
                return ResponseEntity.badRequest().build();
            }

            paymentService.confirmPayment(asaasPaymentId, amount);
        }

        return ResponseEntity.ok().build();
    }

}
