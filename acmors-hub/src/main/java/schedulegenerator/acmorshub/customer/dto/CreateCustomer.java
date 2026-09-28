package schedulegenerator.acmorshub.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomer {
    private String FullName;
    private String Document;
    private String Email;
    private String Password;
    private String Phone;
}
