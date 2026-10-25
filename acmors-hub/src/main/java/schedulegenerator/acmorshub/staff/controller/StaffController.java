package schedulegenerator.acmorshub.staff.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import schedulegenerator.acmorshub.staff.dto.CreateStaff;
import schedulegenerator.acmorshub.staff.dto.ResponseStaff;
import schedulegenerator.acmorshub.staff.dto.UpdateStaff;
import schedulegenerator.acmorshub.staff.service.StaffService;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PostMapping
    public ResponseEntity<ResponseStaff> create(@RequestBody @Valid CreateStaff create) {
        return ResponseEntity.status(HttpStatus.CREATED).body(staffService.create(create));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseStaff> update(@PathVariable Long id, @RequestBody UpdateStaff update) {
        return ResponseEntity.ok().body(staffService.updateStaff(id, update));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStaff> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(staffService.deleteStaffById(id));
    }

}
