package schedulegenerator.acmorshub.payment.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class FakePaymentProviderService {

    //Simula um pagamento com sucesso
    public boolean successPayment(BigDecimal amount){
        return true;
    }
}
