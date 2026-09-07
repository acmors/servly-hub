package schedulegenerator.acmorshub.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schedulegenerator.acmorshub.company.entities.Company;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsCompaniesByName(String name);
    Optional<Company> findCompanyByName(String name);
}
