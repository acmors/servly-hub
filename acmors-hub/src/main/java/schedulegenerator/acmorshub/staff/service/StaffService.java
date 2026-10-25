package schedulegenerator.acmorshub.staff.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedulegenerator.acmorshub.company.entities.Company;
import schedulegenerator.acmorshub.company.service.CompanyService;
import schedulegenerator.acmorshub.exceptions.ResourceNotFoundExeption;
import schedulegenerator.acmorshub.staff.dto.CreateStaff;
import schedulegenerator.acmorshub.staff.dto.ResponseStaff;
import schedulegenerator.acmorshub.staff.dto.StaffMapper;
import schedulegenerator.acmorshub.staff.dto.UpdateStaff;
import schedulegenerator.acmorshub.staff.entities.Staff;
import schedulegenerator.acmorshub.staff.entities.enums.Role;
import schedulegenerator.acmorshub.staff.repository.StaffRepository;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;
    private final CompanyService companyService;

    @Transactional
    public ResponseStaff create(CreateStaff create){
        Company company = companyService.findById(create.getCompanyId());
        Staff staff = new Staff();
        staff.setName(create.getName());
        staff.setEmail(create.getEmail());
        staff.setPassword(create.getPassword());
        staff.setRole(Role.company_employee);
        staff.setCompany(company);
        staffRepository.save(staff);

        return StaffMapper.toDto(staff);
    }

    @Transactional(readOnly = true)
    public Staff findStaffById(Long id){
        return staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("Employed not found"));
    }

    @Transactional
    public ResponseStaff updateStaff(Long id, UpdateStaff update){
        Staff staff = findStaffById(id);
        staff.setName(update.getName());
        staff.setEmail(update.getEmail());

        staffRepository.save(staff);
        return StaffMapper.toDto(staff);
    }

    @Transactional
    public ResponseStaff deleteStaffById(Long id){
        Staff staff = findStaffById(id);
        staffRepository.delete(staff);
        return StaffMapper.toDto(staff);
    }
}
