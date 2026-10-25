package schedulegenerator.acmorshub.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomer {
    private String fullName;
    private String document;
    private String email;
    private String phone;
}
