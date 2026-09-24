package schedulegenerator.acmorshub.staff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schedulegenerator.acmorshub.staff.entities.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}
