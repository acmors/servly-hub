package schedulegenerator.acmorshub.customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedulegenerator.acmorshub.company.entities.Company;
import schedulegenerator.acmorshub.company.service.CompanyService;
import schedulegenerator.acmorshub.customer.dto.CreateCustomer;
import schedulegenerator.acmorshub.customer.dto.CustomerMapper;
import schedulegenerator.acmorshub.customer.dto.ResponseCustomer;
import schedulegenerator.acmorshub.customer.dto.UpdateCustomer;
import schedulegenerator.acmorshub.customer.entities.Customer;
import schedulegenerator.acmorshub.customer.repository.CustomerRepository;
import schedulegenerator.acmorshub.exceptions.ResourceNotFoundExeption;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CompanyService companyService;

    @Transactional
    public ResponseCustomer createCustomer(CreateCustomer customer) {
        Customer newCustomer = new Customer();
        Company company = companyService.findById(customer.getCompanyId());

        newCustomer.setFullName(customer.getFullName());
        newCustomer.setEmail(customer.getEmail());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setPassword(customer.getPassword());
        newCustomer.setDocument(customer.getDocument());
        newCustomer.setCompany(company);

        customerRepository.save(newCustomer);
        return CustomerMapper.toDto(newCustomer);
    }

    @Transactional(readOnly = true)
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExeption("Customer not found"));
    }

    @Transactional
    public ResponseCustomer update(Long id, UpdateCustomer updateCustomer) {
        Customer customer =  findCustomerById(id);

        customer.setFullName(updateCustomer.getFullName());
        customer.setEmail(updateCustomer.getEmail());
        customer.setPhone(updateCustomer.getPhone());
        customer.setDocument(updateCustomer.getDocument());
        customerRepository.save(customer);
        return CustomerMapper.toDto(customer);
    }

    @Transactional
    public ResponseCustomer delete(Long id) {
        Customer customer = findCustomerById(id);
        customerRepository.delete(customer);
        return CustomerMapper.toDto(customer);
    }

}
