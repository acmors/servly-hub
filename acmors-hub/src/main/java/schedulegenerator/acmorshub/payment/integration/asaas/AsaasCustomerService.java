package schedulegenerator.acmorshub.payment.integration.asaas;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import schedulegenerator.acmorshub.customer.entities.Customer;
import schedulegenerator.acmorshub.customer.repository.CustomerRepository;
import schedulegenerator.acmorshub.payment.integration.asaas.dto.AsaasCreateCustomerRequest;
import schedulegenerator.acmorshub.payment.integration.asaas.dto.AsaasCustomerResponse;

@Service
@RequiredArgsConstructor
public class AsaasCustomerService {

    private final AsaasClient asaasClient;
    private final CustomerRepository customerRepository;

    public String getOrCreateAsaasCustomer(Customer customer) {

        //verifica se há um AssasID do cliente e retorna ele, caso exista
        if(customer.getAsaasCustomerId() != null &&  !customer.getAsaasCustomerId().isEmpty()){
            return customer.getAsaasCustomerId();
        }

        //Vai montar o Customer cliente com os dados que já tenho no meu sistema
        AsaasCreateCustomerRequest request = new AsaasCreateCustomerRequest(
                customer.getFullName(),
                customer.getDocument(),
                customer.getEmail(),
                customer.getId().toString()
        );

        //cria o AsaasCustomer
        AsaasCustomerResponse customerResponse = asaasClient.createCustomer(request);
        //Setta o ID do Asaas no cliente
        customer.setAsaasCustomerId(customerResponse.getId());
        customerRepository.save(customer);

        //retorna o ID
        return customerResponse.getId();
    }
}
