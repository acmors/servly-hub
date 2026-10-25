package schedulegenerator.acmorshub.customer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import schedulegenerator.acmorshub.company.entities.Company;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String document;
    private String email;
    private String password;
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;
    //Id que seria criado pelo serviço de pagamento externo
    private String asaasCustomerId;
}
