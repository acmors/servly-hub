package schedulegenerator.acmorshub.product.entities;

import jakarta.persistence.*;
import schedulegenerator.acmorshub.company.entities.Company;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;
}
