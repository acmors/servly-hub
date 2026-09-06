package schedulegenerator.acmorshub.company.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import schedulegenerator.acmorshub.product.entities.Product;
import schedulegenerator.acmorshub.staff.entities.Staff;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String cnpj;
    private boolean active;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Staff> staff;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products;
}
