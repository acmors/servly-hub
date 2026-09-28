package schedulegenerator.acmorshub.customer.dto;

import org.springframework.context.annotation.Configuration;
import schedulegenerator.acmorshub.customer.entities.Customer;

@Configuration
public class CustomerMapper {

    public static ResponseCustomer toDto(Customer customer) {
        return new ResponseCustomer(
                customer.getFullName(),
                customer.getDocument(),
                customer.getEmail(),
                customer.getPhone()
        );
    }
}
