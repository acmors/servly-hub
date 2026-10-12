package schedulegenerator.acmorshub.payment.integration.asaas;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import schedulegenerator.acmorshub.payment.integration.asaas.dto.AsaasCreateCustomerRequest;
import schedulegenerator.acmorshub.payment.integration.asaas.dto.AsaasCustomerResponse;

@Component
@RequiredArgsConstructor
public class AsaasClient {

    private final RestClient asaasClient;

    //vai realizar a criação do client no asaas utilizando os DTOs que criamos
    public AsaasCustomerResponse createCustomer(AsaasCreateCustomerRequest request) {
        return asaasClient.post()
                .uri("/customers")
                .body(request)
                .retrieve()
                .body(AsaasCustomerResponse.class);
    }
}
