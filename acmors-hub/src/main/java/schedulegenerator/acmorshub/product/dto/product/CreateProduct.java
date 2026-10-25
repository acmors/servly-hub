package schedulegenerator.acmorshub.product.dto.product;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProduct {
    @NotBlank(message = "O nome do produto é obrigatório")
    private String name;

    @NotBlank(message = "A descrição do produto é obrigatória")
    private String description;

    @NotBlank(message = "O preço do produto é obrigatório")
    private BigDecimal price;

    private Long companyId;
}
