package com.example.jobsApp.controller;

import java.util.List;
import java.util.Objects;

import com.example.jobsApp.models.Company;
import com.example.jobsApp.repositories.CompanyRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Company", description = "Company API")
@RestController
@RequestMapping("/api/company")
public class CompanyRestController {

	private final Logger log = LoggerFactory.getLogger(getClass());
	final CompanyRepository company;
	
	public CompanyRestController(CompanyRepository company) {
		log.trace("company constructor");
		this.company = company;
	}
	
	@GetMapping(value= {"/test","/test/{name}"})
	public String testConnection(@PathVariable("name") String i) {
		if(i.equals("marco")) {
			return "polo";
		}
		return "Test successful";
	}
	
	@GetMapping("/stat/total")
	public ResponseEntity<String> totalNumberCompanies() {
		log.trace("Counting Companies");
		long c = company.count();
		return new ResponseEntity<>(String.valueOf(c),HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Company>> allCompanies() {
		List<Company> a = company.findAll();
		log.trace("Looked for Companies");
		return ResponseEntity.ok(a);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getCompanyByID(@PathVariable("id") Long id) {
		log.trace("Search Company by ID: "+id);
		
		if (!company.existsById(id)) {
            return new ResponseEntity<>("{\"error\":\"Company not found.\"}", HttpStatus.NOT_FOUND);
        }
		//company.findAll().stream().filter(t -> t.getId()==id).toList().get(0)
		return new ResponseEntity<>(company.findById(id), HttpStatus.OK);
		//return a.get(0);
		//return ;
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<?> addNewCompany(@RequestBody CompanyDto newComp) {
		log.trace("Creating Company");
		
		if(Objects.isNull(newComp.id())){
			return new ResponseEntity<>("Id must be null",HttpStatus.BAD_REQUEST);
		}
		
		Company comp = new Company(
				newComp.name(),
				newComp.logo_url(),
				newComp.address(),
				newComp.plz(),
				newComp.ort(),
				newComp.website());
		
		comp = company.save(comp);
		if(comp==null) {
			return new ResponseEntity<>("Error creating new Company", HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			return new ResponseEntity<>(comp, HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping(value = "/remove/{id}")
	public ResponseEntity<String> removeCompany(@PathVariable("id") Long id){
		log.trace("Deleteing company by id "+id);
	    
		if (!company.existsById(id)) {
            return new ResponseEntity<>("{\"error\":\"Company not found.\"}", HttpStatus.NOT_FOUND);
        }
		
		try {
	        company.deleteById(id);
	        String s = "{\"deleted\":" + id + "}";
	        return new ResponseEntity<>(s, HttpStatus.OK);
	    } catch (DataIntegrityViolationException e) {
	        // Handle the exception, e.g., return a meaningful error response
	        return new ResponseEntity<>("{\"error\":\"Referential integrity constraint violation.\"}", HttpStatus.BAD_REQUEST);
	    }
	}
	
	
	/*@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCompanyById(@RequestBody CompanyDto compUpd, @PathVariable("id") long id){
		log.trace("Updating Company by id "+id);
		if (!company.existsById(id)) {
            return new ResponseEntity<>("{\"error\":\"Company not found.\"}", HttpStatus.NOT_FOUND);
        }
		return new ResponseEntity<>("{\"error\":\"Update not working atm\"}", HttpStatus.BAD_REQUEST);
		//company.findById(null))
	}//*/
	

}

/*
/company		plz,ort,name
/job 			title,pensum, typ, sort
/extras 		remote, flexibel, signup, devices, extralohn

/job/extra  	/job, /extras
/company/extra	/company, /extras

/jobs
/companies



//*/



