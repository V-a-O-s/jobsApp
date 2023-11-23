package com.example.jobsApp.controller;

import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobsApp.models.Job;
import com.example.jobsApp.repositories.CompanyRepository;
import com.example.jobsApp.repositories.JobRepository;

//import com.example.jobsApp.models.Company;
//import com.example.jobsApp.repositories.CompanyRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Job", description = "The Job API")
@RestController
@RequestMapping("/api/jobs")
public class JobsAppRestController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	final JobRepository jobs;

	public JobsAppRestController(JobRepository jobs) {
		log.trace("calling constructor");
		this.jobs = jobs;
	}
	
	CompanyRepository company;
	
	public void CompanyRestController(CompanyRepository company) {
		log.trace("company constructor");
		this.company = company;
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody JobDto newJobDto) {
	    if (newJobDto.anzahl() >= 1) {
	    	long a = newJobDto.company_id();
			if (company.existsById(a)) {
			    Job newJob = new Job(
			            newJobDto.title(),
			            newJobDto.description(),
			            //newJobDto.company_id(),
			            a,
			            newJobDto.anzahl(),
			            newJobDto.status(),
			            newJobDto.pensum()
			    );

			    newJob = jobs.save(newJob);
			    return ResponseEntity.created(URI.create("/api/jobs/" + newJob.getId())).body(JobDto.fromDomain(newJob));
			} else {
			    return new ResponseEntity<>("{\"error\":\"Company not found.\"}", HttpStatus.BAD_REQUEST);
			}
	    }

	    return new ResponseEntity<>("{\"error\":\"Invalid anzahl value.\"}", HttpStatus.BAD_REQUEST);
	}


	@GetMapping("/stat/total")
	public String totalNumberofJobs() {
		long nofJobs = jobs.count();
		log.trace("number of jobs: " + nofJobs);
		return String.valueOf(nofJobs);

	}

// bad: -> paging!
	@Operation(summary = "Fetch all jobs. ", description = "fetches all job entities from the datasource.")
	@GetMapping("/all")
	public ResponseEntity<?> jobs() {
		return ResponseEntity.ok(jobs.findAll());
	}

	@Operation(summary = "Fetch filtered ID.", description = "fetches all job entities from the datasource matching ID.")
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> get(@PathVariable long id) {
		Optional<JobDto> maybeJob = jobs.findById(id).map(JobDto::fromDomain);
		return ResponseEntity.ok(maybeJob);
	}
}
