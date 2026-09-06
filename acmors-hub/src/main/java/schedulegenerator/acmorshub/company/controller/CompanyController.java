package schedulegenerator.acmorshub.company.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import schedulegenerator.acmorshub.company.entities.Company;
import schedulegenerator.acmorshub.company.service.CompanyService;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<Company> create(@RequestBody Company company) {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.create(company));
    }

    @PutMapping("/update/{companyId}")
    public ResponseEntity<Company> update(@PathVariable Long companyId, @RequestBody Company company) {
        return ResponseEntity.ok(companyService.update(company, companyId));
    }

}
