package schedulegenerator.acmorshub.company.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCompany {
    private String name;
    private String phone;
    private String email;
    private String cnpj;
}
