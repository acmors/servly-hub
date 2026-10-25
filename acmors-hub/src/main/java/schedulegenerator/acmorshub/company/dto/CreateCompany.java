package schedulegenerator.acmorshub.company.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCompany {

    @NotBlank(message = "O campo Nome é obrigatório")
    private String name;

    @NotBlank(message = "O campo Telefone é obrigatório")
    private String phone;

    @NotBlank(message = "O campo E-mail é obrigatório")
    private String email;

    @CNPJ(message = "CNPJ inválido")
    @NotBlank(message = "O campo CNPJ é obrigatório")
    private String cnpj;
}
