package schedulegenerator.acmorshub.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import schedulegenerator.acmorshub.company.entities.Company;
import schedulegenerator.acmorshub.company.repository.CompanyRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company create(Company company) {
        var existsByName = companyRepository.existsCompaniesByName(company.getName());
        if (existsByName) throw new RuntimeException("Company with name " + company.getName() + " already exists");

        return companyRepository.save(company);
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
