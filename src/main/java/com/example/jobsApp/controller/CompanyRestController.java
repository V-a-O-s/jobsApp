package com.example.jobsApp.controller;

import com.example.jobsApp.models.Company;
import com.example.jobsApp.repositories.CompanyRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Optional;

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

	@GetMapping("/stat/total")
	public ResponseEntity<String> totalNumberCompanies() {
		log.trace("Counting Companies");
		long c = company.count();
		return new ResponseEntity<>(String.valueOf(c), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> allCompanies() {
		log.trace("Looked for Companies");
		return ResponseEntity.ok(company.findAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getCompanyByID(@PathVariable("id") Long id) {
		log.trace("Search Company by ID: " + id);

		if (!company.existsById(id)) {
			return new ResponseEntity<>("Company not found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(company.findById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> addNewCompany(@RequestBody CompanyDto newComp) {
		log.trace("Creating Company");

		if (newComp.id() != null) {
			return new ResponseEntity<>("Id must be null", HttpStatus.BAD_REQUEST);
		}

		Company comp = new Company(newComp.name(), newComp.logo_url(), newComp.address(), newComp.plz(), newComp.ort(),
				newComp.website());

		comp = company.save(comp);
		if (comp == null) {
			return new ResponseEntity<>("Error creating new Company", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<>(comp, HttpStatus.CREATED);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> removeCompany(@PathVariable("id") Long id) {
		log.trace("Deleteing company by id " + id);

		if (!company.existsById(id)) {
			return new ResponseEntity<>("Company not found.", HttpStatus.NOT_FOUND);
		}

		try {
			company.deleteById(id);
			String s = "deleted: " + id;
			return new ResponseEntity<>(s, HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>("Referential integrity constraint violation.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		if (companyDto.id() != null && companyDto.id() != id) {
			return new ResponseEntity<>("Path variable of id is not equal to company id", HttpStatus.CONFLICT);
		}

		Optional<Company> maybeCompany = company.findById(id);
		if (maybeCompany.isEmpty()) {
			return new ResponseEntity<>("Unable to update company with given id " + id, HttpStatus.NOT_FOUND);
		}

		Company comp = maybeCompany.get();
		comp.setName(companyDto.name());
		comp.setLogoUrl(companyDto.logo_url());
		comp.setAddress(companyDto.address());
		comp.setPlz(companyDto.plz());
		comp.setOrt(companyDto.ort());
		comp.setWebsite(companyDto.website());

		comp = company.save(comp);

		return ResponseEntity.ok(comp);
	}
}
