package schedulegenerator.acmorshub.staff.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import schedulegenerator.acmorshub.staff.dto.CreateStaff;
import schedulegenerator.acmorshub.staff.dto.ResponseStaff;
import schedulegenerator.acmorshub.staff.entities.Staff;
import schedulegenerator.acmorshub.staff.service.StaffService;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PostMapping
    public ResponseEntity<ResponseStaff> create(@RequestBody CreateStaff create) {
        return ResponseEntity.status(HttpStatus.CREATED).body(staffService.create(create));
    }

}
