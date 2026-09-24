package schedulegenerator.acmorshub.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedulegenerator.acmorshub.company.dto.CompanyMapper;
import schedulegenerator.acmorshub.company.dto.CreateCompany;
import schedulegenerator.acmorshub.company.dto.ResponseCompany;
import schedulegenerator.acmorshub.company.dto.UpdateCompany;
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

    @Transactional(readOnly = true)
    public Company findById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company with id " + id + " not found"));
    }

    @Transactional(readOnly = true)
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Transactional
    public ResponseCompany update(UpdateCompany company, Long companyId) {
        Company update = findById(companyId);
        update.setName(company.getName());
        update.setEmail(company.getEmail());
        update.setPhoneNumber(company.getPhone());
        update.setCnpj(company.getCnpj());

        var updated = companyRepository.save(update);
        return CompanyMapper.toDto(updated);
    }

    @Transactional(readOnly = true)
    public ResponseCompany findCompanyByName(String name) {
        var find = companyRepository.findCompanyByName(name).orElseThrow(() -> new RuntimeException("Company with name " + name + " not found"));
        return CompanyMapper.toDto(find);
    }

    public void delete(Company company) {
        companyRepository.delete(company);
    }
}
