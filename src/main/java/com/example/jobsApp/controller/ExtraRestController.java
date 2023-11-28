package com.example.jobsApp.controller;

import java.util.Optional;

import org.hibernate.mapping.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobsApp.models.Company;
import com.example.jobsApp.models.Extra;
import com.example.jobsApp.models.Job;
import com.example.jobsApp.repositories.ExtraRepository;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

public class ExtraRestController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	final ExtraRepository extra;

public ExtraRestController(ExtraRepository extra) {
	log.trace("extra constructor");
	this.extra = extra;
}

@GetMapping(value= {"/test","/test/{name}"})
public String testConnection(@PathVariable("name") String i) {
	if(i.equals("marco")) {
		return "polo";
	}
	return "Test successful";
}

@GetMapping("/stat/total")
public ResponseEntity<String> totalNumberExtras(){
	log.trace("Counting Extras");
	long c = extra.count();
	return new ResponseEntity<>(String.valueOf(c),HttpStatus.OK);
}

@GetMapping("/all")
public ResponseEntity<?> allExtras(){
	log.trace("Looked for Extras");
	return ResponseEntity.ok(extra.findAll());
}

@GetMapping(value = "/{id}")
public ResponseEntity<?> getExtraByID(@PathVariable("id")Long id){
	log.trace("Search Extra by ID: "+id);
	
	if (!extra.existsById(id)) {
		return new ResponseEntity<>(extra.findById(id),HttpStatus.OK);
	}
	return new ResponseEntity<>(extra.findById(id), HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<Extra> addNewCompany(@RequestBody ExtraDto newComp) {
		log.trace("Creating Extra");
		Extra ext = new Extra(newComp.remote(),newComp.flexibel(),newComp.signup(),newComp.devices(),newComp.extrapay(),newComp.sonstiges());
		ext = extra.save(ext);
		if(ext==null) {
			return new ResponseEntity<>(ext, HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			return new ResponseEntity<>(ext, HttpStatus.CREATED);
	
		}
	}
	@DeleteMapping(value = "/remove/{id}")
	public ResponseEntity<String> removeExtra(@PathVariable("id") Long id){
		log.trace("Deleteing extra by id "+id);
	    
		if (!extra.existsById(id)) {
            return new ResponseEntity<>("{\"error\":\"Extra not found.\"}", HttpStatus.NOT_FOUND);
        }
		
		try {
	        extra.deleteById(id);
	        String s = "{\"deleted\":" + id + "}";
	        return new ResponseEntity<>(s, HttpStatus.OK);
	    } catch (DataIntegrityViolationException e) {
	        // Handle the exception, e.g., return a meaningful error response
	        return new ResponseEntity<>("{\"error\":\"Referential integrity constraint violation.\"}", HttpStatus.BAD_REQUEST);
	    }
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateExtraById(@RequestBody ExtraDto extUpd, @PathVariable("id") long id){
		log.trace("Updating Extra by id "+id);
		if (!extra.existsById(id)) {
            return new ResponseEntity<>("{\"error\":\"Extra not found.\"}", HttpStatus.NOT_FOUND);
        }
		return new ResponseEntity<>("{\"error\":\"Update not working atm\"}", HttpStatus.BAD_REQUEST);
		//company.findById(null))
	}
	
	
}