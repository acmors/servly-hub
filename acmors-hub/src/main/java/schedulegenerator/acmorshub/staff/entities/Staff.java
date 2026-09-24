package schedulegenerator.acmorshub.staff.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import schedulegenerator.acmorshub.company.entities.Company;
import schedulegenerator.acmorshub.staff.entities.enums.Role;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
}
