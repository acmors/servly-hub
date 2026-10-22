package schedulegenerator.acmorshub.payment.integration.asaas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AsaasWebhookPayload (
        String id,
        String event,
        PaymentData payment
){
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record PaymentData(
            String id,
            String customer,
            BigDecimal value,
            BigDecimal netValue,
            String billingType,
            String status
    ){}
}
