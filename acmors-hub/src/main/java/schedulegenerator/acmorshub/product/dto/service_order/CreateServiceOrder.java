package schedulegenerator.acmorshub.product.dto.service_order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateServiceOrder {
    private Long customerId;
    private Long companyId;
    private Long staffId;
    private String note;
    private List<CreateOrderServiceItem> items;
}
