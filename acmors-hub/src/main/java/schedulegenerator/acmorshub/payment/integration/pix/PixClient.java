package schedulegenerator.acmorshub.payment.integration.pix;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PixClient {

    private final RestClient client;

    public PixClient() {
        this.client = RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

    public PixChargeResponse charge(PixChargeRequest pixChargeRequest) {
        return client.post()
                .uri("/cob")
                .contentType(MediaType.APPLICATION_JSON)
                .body(pixChargeRequest)
                .retrieve()
                .body(PixChargeResponse.class);
    }

    public void pay(String txid){
        client.post()
                .uri("/sandbox/pay")
                .contentType(MediaType.APPLICATION_JSON)
                .body(new PixPayRequest(txid, "Pagamento Servly"))
                .retrieve()
                .toBodilessEntity();
    }
}
