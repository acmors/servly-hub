package schedulegenerator.acmorshub.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCustomer {
    private Long id;
    private String fullName;
    private String document;
    private String email;
    private String phone;
    private Long companyId;
}
