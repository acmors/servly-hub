package schedulegenerator.acmorshub.staff.dto;

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

    private String name;
    private String email;
    private String password;
    private Role role;
    private Long companyId;
}
