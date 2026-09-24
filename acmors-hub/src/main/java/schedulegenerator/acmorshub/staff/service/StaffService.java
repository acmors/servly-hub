package schedulegenerator.acmorshub.staff.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedulegenerator.acmorshub.company.entities.Company;
import schedulegenerator.acmorshub.company.repository.CompanyRepository;
import schedulegenerator.acmorshub.staff.dto.CreateStaff;
import schedulegenerator.acmorshub.staff.dto.ResponseStaff;
import schedulegenerator.acmorshub.staff.dto.StaffMapper;
import schedulegenerator.acmorshub.staff.entities.Staff;
import schedulegenerator.acmorshub.staff.entities.enums.Role;
import schedulegenerator.acmorshub.staff.repository.StaffRepository;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public ResponseStaff create(CreateStaff create){
        Company company = companyRepository.findById(create.getCompanyId()).orElseThrow(() -> new RuntimeException("Company not found"));
        Staff staff = new Staff();
        staff.setName(create.getName());
        staff.setEmail(create.getEmail());
        staff.setPassword(create.getPassword());
        staff.setRole(Role.company_employee);
        staffRepository.save(staff);

        return StaffMapper.toDto(staff);
    }
}
