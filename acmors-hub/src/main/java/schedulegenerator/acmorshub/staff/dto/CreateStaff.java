package schedulegenerator.acmorshub.staff.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import schedulegenerator.acmorshub.staff.entities.enums.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateStaff {

    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @NotBlank(message = "O Email é obrigatório")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String password;

    private Role role;
    private Long companyId;
}
