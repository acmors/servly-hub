package schedulegenerator.acmorshub.payment.integration.asaas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsaasCustomerResponse {

    //Id que o Asaas vai criar para nosso cliente no sistema deles
    private String id;
    private String name;
    private String email;

}
