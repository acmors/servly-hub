package schedulegenerator.acmorshub.company.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import schedulegenerator.acmorshub.company.dto.CreateCompany;
import schedulegenerator.acmorshub.company.dto.ResponseCompany;
import schedulegenerator.acmorshub.company.dto.UpdateCompany;
import schedulegenerator.acmorshub.company.entities.Company;
import schedulegenerator.acmorshub.company.service.CompanyService;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<ResponseCompany> create(@RequestBody CreateCompany company) {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.create(company));
    }

    @PutMapping("/update/{companyId}")
    public ResponseEntity<ResponseCompany> update(@PathVariable Long companyId, @RequestBody UpdateCompany company) {
        return ResponseEntity.ok(companyService.update(company, companyId));
    }

    @GetMapping("/name/{companyName}")
    public ResponseEntity<ResponseCompany> getByName(@PathVariable String companyName) {
        return ResponseEntity.ok(companyService.findCompanyByName(companyName));
    }
}
