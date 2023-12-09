package com.example.jobsApp.controller;

import com.example.jobsApp.models.Extra;
import com.example.jobsApp.repositories.ExtraRepository;

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

@Tag(name = "Extras", description = "The Extras API")
@RestController
@RequestMapping("/api/extras")
public class ExtraRestController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	final ExtraRepository extra;

	public ExtraRestController(ExtraRepository extra) {
		log.trace("extra constructor");
		this.extra = extra;
	}

	@GetMapping("/stat/total")
	public ResponseEntity<String> totalNumberExtras(){
		log.trace("Counting Extras");
		long c = extra.count();
		return new ResponseEntity<>(String.valueOf(c),HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> allExtras(){
		log.trace("Looked for Extras");
		return ResponseEntity.ok(extra.findAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getExtraByID(@PathVariable("id")Long id){
		log.trace("Search Extra by ID: "+id);

		if (!extra.existsById(id)) {
			return new ResponseEntity<>("Extra not found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(extra.findById(id), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<?> addNewExtra(@RequestBody ExtraDto newExtra) {
		log.trace("Creating Extra");
		
		if(newExtra.id()!=null){
			return new ResponseEntity<>("Id must be null",HttpStatus.BAD_REQUEST);
		}
		
		Extra ext = new Extra(
				newExtra.remote(),
				newExtra.flexibel(),
				newExtra.signUp(),
				newExtra.devices(),
				newExtra.extrapay(),
				newExtra.sonstiges());
		
		ext = extra.save(ext);
		if(ext==null) {
			return new ResponseEntity<>(ext, HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			return new ResponseEntity<>(ext, HttpStatus.CREATED);

		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> removeExtra(@PathVariable("id") Long id){
		log.trace("Deleteing extra by id "+id);

		if (!extra.existsById(id)) {
			return new ResponseEntity<>("Extra not found.", HttpStatus.NOT_FOUND);
		}

		try {
			extra.deleteById(id);
			String s = "deleted: " + id ;
			return new ResponseEntity<>(s, HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>("Referential integrity constraint violation.", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateExtra(@PathVariable long id, @RequestBody ExtraDto extraDto) {
	    if (extraDto.id() != null && extraDto.id() != id) {
	        return new ResponseEntity<>("Path variable of id is not equal to extra id", HttpStatus.CONFLICT);
	    }
	    Optional<Extra> maybeExtra = extra.findById(id);
	    if (maybeExtra.isEmpty()) {
	        return new ResponseEntity<>("Unable to update extra with given id " + id, HttpStatus.NOT_FOUND);
	    }
	    Extra ext = maybeExtra.get();
	    ext.setRemote(extraDto.remote());
	    ext.setFlexibel(extraDto.flexibel());
	    ext.setSignUp(extraDto.signUp());
	    ext.setDevices(extraDto.devices());
	    ext.setExtrapay(extraDto.extrapay());
	    ext.setSonstiges(extraDto.sonstiges());

	    ext = extra.save(ext);

	    return ResponseEntity.ok(ext);
	}


}