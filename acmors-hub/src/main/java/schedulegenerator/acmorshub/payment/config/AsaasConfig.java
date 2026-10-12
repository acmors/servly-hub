package schedulegenerator.acmorshub.payment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

//Classe que é configurada para chamar a API externa
@Configuration
public class AsaasConfig {

    @Bean("asaasRestClient")
    public RestClient restClient(@Value("${asaas.base-url}") String baseUrl, @Value("${asaas.api-key}") String apiKey) {

        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("access_token", apiKey)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("User-Agent", "Servly/1.0")
                .build();
    }
}
