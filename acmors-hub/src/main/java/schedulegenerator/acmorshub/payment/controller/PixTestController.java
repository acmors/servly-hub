package schedulegenerator.acmorshub.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import schedulegenerator.acmorshub.payment.integration.pix.PixChargeRequest;
import schedulegenerator.acmorshub.payment.integration.pix.PixChargeResponse;
import schedulegenerator.acmorshub.payment.integration.pix.PixClient;

@RestController
@RequestMapping("/api/test/pix")
@RequiredArgsConstructor
public class PixTestController {
    private final PixClient pixClient;

    @PostMapping("/charge")
    public PixChargeResponse createCharge() {

        PixChargeRequest request =
                new PixChargeRequest(
                        new PixChargeRequest.Amount("10.00"),
                        "dev@example.com"
                );

        return pixClient.charge(request);
    }
}
