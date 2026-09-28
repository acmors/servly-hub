package schedulegenerator.acmorshub.customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import schedulegenerator.acmorshub.customer.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;


}
