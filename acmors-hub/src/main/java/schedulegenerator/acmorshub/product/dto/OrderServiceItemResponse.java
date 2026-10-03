package schedulegenerator.acmorshub.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderServiceItemResponse {
    private Long productId;
    private Integer quantity;
    private BigDecimal discount;
}
