package schedulegenerator.acmorshub.payment.integration.pix;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PixChargeResponse {

    private String txid;
    private String status;
    private String pixCopiaECola;
}
