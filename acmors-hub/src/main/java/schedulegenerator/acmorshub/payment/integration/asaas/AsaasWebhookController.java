package schedulegenerator.acmorshub.payment.integration.asaas;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import schedulegenerator.acmorshub.payment.services.PaymentService;

@RestController
@RequestMapping("/webhooks/asaas")
public class AsaasWebhookController {
    
    private static final Logger log = LoggerFactory.getLogger(AsaasWebhookController.class);

    private final PaymentService paymentService;
    private final String expectedToken;

    public AsaasWebhookController(PaymentService paymentService, @Value("${asaas.webhook.token}") String expectedToken) {
        this.paymentService = paymentService;
        this.expectedToken = expectedToken;
    }

    @PostMapping
    public ResponseEntity<Void> receive(
            @RequestHeader(value = "asaas-access-token", required = false) String token,
            @RequestBody AsaasWebhookPayload payload
    ){
        if(!expectedToken.equals(token)){
            log.warn("Webhook rejeitado: token recebido é inválido (event={})", payload.event());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        log.info("Webhook Asaas recebido: event={}, paymentId={}", payload.event(), payload.payment());

        if(payload.payment() == null){
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        boolean isPaidEvent = "PAYMENT_RECEIVED".equals(payload.event()) || "PAYMENT_CONFIRMED".equals(payload.event());

        if(!isPaidEvent){
            log.info("Evento ignorado: event={}, paymentId={}", payload.event(), payload.payment());
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        try{
            paymentService.confirmPayment(
                    payload.payment().id(),
                    payload.payment().value().toPlainString()
            );
            log.info("Pagamento recebido: event={}, paymentId={}", payload.event(), payload.payment());
        } catch(Exception e){
            log.error("Erro ao processar webhook do pagamento {}: {}", payload.payment().id(), e);
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
