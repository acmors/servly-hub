package schedulegenerator.acmorshub.product.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProduct {

    private String name;
    private String description;
    private Double price;
}
