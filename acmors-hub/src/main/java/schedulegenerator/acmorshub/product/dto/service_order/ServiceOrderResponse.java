package schedulegenerator.acmorshub.product.dto.service_order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import schedulegenerator.acmorshub.product.entities.enums.ServiceOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceOrderResponse {

    private Long companyId;
    private Long customerId;
    private Long staffId;
    private String note;
    private List<OrderServiceItemResponse> createOrderServiceItems;
    private ServiceOrderStatus serviceOrderStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BigDecimal totalPrice;

}
