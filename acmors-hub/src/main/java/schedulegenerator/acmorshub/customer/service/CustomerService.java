package schedulegenerator.acmorshub.customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedulegenerator.acmorshub.customer.dto.CreateCustomer;
import schedulegenerator.acmorshub.customer.dto.CustomerMapper;
import schedulegenerator.acmorshub.customer.dto.ResponseCustomer;
import schedulegenerator.acmorshub.customer.entities.Customer;
import schedulegenerator.acmorshub.customer.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Transactional
    public ResponseCustomer createCustomer(CreateCustomer customer) {
        Customer newCustomer = new Customer();
        newCustomer.setFullName(customer.getFullName());
        newCustomer.setEmail(customer.getEmail());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setPassword(customer.getPassword());
        newCustomer.setDocument(customer.getDocument());

        customerRepository.save(newCustomer);
        return CustomerMapper.toDto(newCustomer);
    }

}
