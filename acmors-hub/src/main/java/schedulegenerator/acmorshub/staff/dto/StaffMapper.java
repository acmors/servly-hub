package schedulegenerator.acmorshub.staff.dto;

import org.springframework.stereotype.Component;
import schedulegenerator.acmorshub.staff.entities.Staff;

@Component
public class StaffMapper {

    public static ResponseStaff toDto(Staff staff){
        return new ResponseStaff(
                staff.getName(),
                staff.getEmail()
        );
    }
}
