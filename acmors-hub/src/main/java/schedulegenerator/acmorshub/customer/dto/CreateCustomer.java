package schedulegenerator.acmorshub.customer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomer {
    @NotBlank(message = "O campo Nome é obrigatório")
    private String fullName;

    @NotBlank(message = "O campo Documento é obrigatório")
    private String document;

    @NotBlank(message = "O campo Email é obrigatório")
    private String email;

    @NotBlank(message = "O campo Senha é obrigatório")
    private String password;

    @NotBlank(message = "O campo Telefone é obrigatório")
    private String phone;

    private Long companyId;
}
