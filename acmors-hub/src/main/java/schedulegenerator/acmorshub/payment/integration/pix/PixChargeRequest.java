package schedulegenerator.acmorshub.payment.integration.pix;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PixChargeRequest {
    private Amount valor;
    private String chave;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Amount{
        private String original;
    }
}


