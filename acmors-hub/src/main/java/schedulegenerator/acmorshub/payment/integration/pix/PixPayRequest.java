package schedulegenerator.acmorshub.payment.integration.pix;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PixPayRequest {

    private String txid;
    private String infoPagador;
}
