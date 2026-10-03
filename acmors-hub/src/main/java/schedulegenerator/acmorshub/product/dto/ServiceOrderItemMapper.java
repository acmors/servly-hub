package schedulegenerator.acmorshub.product.dto;

import org.springframework.stereotype.Component;
import schedulegenerator.acmorshub.product.entities.ServiceOrderItem;

@Component
public class ServiceOrderItemMapper {

    public static OrderServiceItemResponse toDto(ServiceOrderItem serviceOrderItem) {
        return new OrderServiceItemResponse(
                serviceOrderItem.getProduct().getId(),
                serviceOrderItem.getQuantity(),
                serviceOrderItem.getDiscount()
        );
    }
}
