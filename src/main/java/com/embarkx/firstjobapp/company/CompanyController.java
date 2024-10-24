package com.embarkx.firstjobapp.company;


import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity <List<Company>> getAllCompanies(){
        return new ResponseEntity<List<Company>>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company){
        companyService.updateCompany(company, id);
        return new ResponseEntity<>("company updated successfully", HttpStatus.OK);
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> addCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("company added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        boolean isDeleted = companyService.deleteCompanyById(id);
        if (isDeleted){
            return new ResponseEntity<>("company successfully deleted", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("company not found", HttpStatus.OK);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id){
        Company company =companyService.getCompanyId(id);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>((HttpStatus.NOT_FOUND));
            }
        }


}

