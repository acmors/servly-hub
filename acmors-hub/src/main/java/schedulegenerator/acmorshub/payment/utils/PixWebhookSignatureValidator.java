package schedulegenerator.acmorshub.payment.utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

@Component
public class PixWebhookSignatureValidator {

    private final String secret;

    public PixWebhookSignatureValidator( @Value("${pix.webhook.secret:pix-sandbox}") String secret) {
        this.secret = secret;
    }

    public boolean validateSignature(String rawBody, String signature) throws NoSuchAlgorithmException {
        if (rawBody == null || signature == null) {
            return false;
        }

        try{
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(key);

            byte[] calculatedSignature = mac.doFinal(
                    rawBody.getBytes(StandardCharsets.UTF_8)
            );

            byte[] receiveBytes = HexFormat.of().parseHex(signature.trim());

            return MessageDigest.isEqual(calculatedSignature, receiveBytes);

        } catch (Exception e) {
            return false;
        }
    }
}
