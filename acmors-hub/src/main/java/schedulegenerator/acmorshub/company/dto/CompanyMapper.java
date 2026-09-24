package schedulegenerator.acmorshub.company.dto;

import org.springframework.stereotype.Component;
import schedulegenerator.acmorshub.company.entities.Company;

@Component
public class CompanyMapper {

    public static ResponseCompany toDto(Company company) {
        return new ResponseCompany(
                company.getId(),
                company.getName(),
                company.getPhoneNumber(),
                company.getEmail(),
                company.getCnpj()
        );
    }
}
