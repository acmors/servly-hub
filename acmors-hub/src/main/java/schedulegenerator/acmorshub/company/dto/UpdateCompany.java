package schedulegenerator.acmorshub.company.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCompany {
    private String name;
    private String phone;
    private String email;
    private String cnpj;
}
