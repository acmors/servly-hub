package schedulegenerator.acmorshub.company.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import schedulegenerator.acmorshub.company.dto.CompanyMapper;
import schedulegenerator.acmorshub.company.dto.CreateCompany;
import schedulegenerator.acmorshub.company.dto.ResponseCompany;
import schedulegenerator.acmorshub.company.entities.Company;
import schedulegenerator.acmorshub.company.repository.CompanyRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public ResponseCompany create(CreateCompany create) {
        boolean existsByName = companyRepository.existsCompaniesByName(create.getName());
        if (existsByName) throw new RuntimeException("Company with name " + create.getName() + " already exists");

        Company company = new Company();
        company.setName(create.getName());
        company.setPhoneNumber(create.getPhone());
        company.setEmail(create.getEmail());
        company.setCnpj(create.getCnpj());

        var created = companyRepository.save(company);

        return CompanyMapper.toDto(created);
    }

    public Company findById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company with id " + id + " not found"));
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company update(Company company, Long companyId) {
        Company update = findById(companyId);
        update.setName(company.getName());
        update.setEmail(company.getEmail());
        update.setPhoneNumber(company.getPhoneNumber());

        return companyRepository.save(update);
    }

    public void delete(Company company) {
        companyRepository.delete(company);
    }
}
