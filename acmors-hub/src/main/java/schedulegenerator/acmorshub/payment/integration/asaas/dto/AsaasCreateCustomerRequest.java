package schedulegenerator.acmorshub.payment.integration.asaas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//A API do ASAAS exige que seja criado um cliente na plataforma, seria o pagador
//seria uma cópia do cliente que temos na nossa API
//Isso fica salvo para auditoria e pagamentos futuros do mesmo cliente
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsaasCreateCustomerRequest {

    private String name;
    private String cpfCnpj;
    private String email;
    //Id do cliente do nosso sistema
    private String externalReference;
}
