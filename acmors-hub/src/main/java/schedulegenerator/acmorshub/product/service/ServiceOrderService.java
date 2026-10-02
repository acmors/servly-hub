package schedulegenerator.acmorshub.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import schedulegenerator.acmorshub.company.entities.Company;
import schedulegenerator.acmorshub.company.repository.CompanyRepository;
import schedulegenerator.acmorshub.customer.entities.Customer;
import schedulegenerator.acmorshub.customer.repository.CustomerRepository;
import schedulegenerator.acmorshub.product.dto.CreateOrderServiceItem;
import schedulegenerator.acmorshub.product.dto.CreateServiceOrder;
import schedulegenerator.acmorshub.product.entities.Product;
import schedulegenerator.acmorshub.product.entities.ServiceOrder;
import schedulegenerator.acmorshub.product.entities.ServiceOrderItem;
import schedulegenerator.acmorshub.product.repository.ProductRepository;
import schedulegenerator.acmorshub.product.repository.ServiceOrderRepository;
import schedulegenerator.acmorshub.staff.entities.Staff;
import schedulegenerator.acmorshub.staff.repository.StaffRepository;

@Service
@RequiredArgsConstructor
public class ServiceOrderService {

    private final ServiceOrderRepository serviceOrderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;
    private final CompanyRepository companyRepository;

    public ServiceOrder createOrder(CreateServiceOrder create) {

        Customer customer = customerRepository.findById(create.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));

        Staff staff = staffRepository.findById(create.getStaffId())
                .orElseThrow(() -> new RuntimeException("Staff Not Found"));

        Company company = companyRepository.findById(create.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company Not Found"));

        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setCustomer(customer);
        serviceOrder.setStaff(staff);
        serviceOrder.setNote(create.getNote());
        serviceOrder.setCompany(company);

        for(CreateOrderServiceItem itemRequest : create.getItems()){

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product Not Found"));

            ServiceOrderItem item = new ServiceOrderItem();
            item.setProduct(product);
            item.setQuantity(itemRequest.getQuantity());
            item.setUnitPrice(product.getPrice());
            item.setDiscount(itemRequest.getDiscount());

            serviceOrder.addItem(item);
        }

        return serviceOrderRepository.save(serviceOrder);
    }
}
