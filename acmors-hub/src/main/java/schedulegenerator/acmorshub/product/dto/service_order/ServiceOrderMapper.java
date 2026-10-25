package schedulegenerator.acmorshub.product.dto.service_order;

import org.springframework.stereotype.Component;
import schedulegenerator.acmorshub.product.entities.ServiceOrder;

import java.util.List;

@Component
public class ServiceOrderMapper {

    public static ServiceOrderResponse toDTO(ServiceOrder serviceOrder) {

        List<OrderServiceItemResponse> items = serviceOrder.getItems()
                .stream()
                .map(ServiceOrderItemMapper::toDto)
                .toList();

        return new ServiceOrderResponse(
                serviceOrder.getCompany().getId(),
                serviceOrder.getCustomer().getId(),
                serviceOrder.getStaff().getId(),
                serviceOrder.getNote(),
                items,
                serviceOrder.getServiceOrderStatus(),
                serviceOrder.getCreatedAt(),
                serviceOrder.getUpdatedAt(),
                serviceOrder.getTotalPrice()
        );
    }
}
