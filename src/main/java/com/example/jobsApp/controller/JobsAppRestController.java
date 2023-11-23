package com.example.jobsApp.controller;


import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobsApp.models.Job;
import com.example.jobsApp.repositories.JobRepository;

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
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody JobDto jobDto){
		//log.trace("Anzahl:"+jobDto.anzahl());
		log.trace("Pensum:"+jobDto.title());
		if (jobDto.id() != null)
			return ResponseEntity.badRequest().body("ID of job must be null");
		Job newJob = new Job(jobDto.title(),"Hallo",1,"Hallo");
		newJob = jobs.save(newJob);
		
		log.trace("Anzahl:"+newJob.getAnzahl());
		
		return ResponseEntity.created(URI.create("/api/jobs/" + newJob.getId()))
				.body(JobDto.fromDomain(newJob));
		

	}
	
@RequestMapping("/stat/total")
public String totalNumberofJobs() {
	long nofJobs = jobs.count();
	log.trace("number of jobs: " + nofJobs);
	return String.valueOf(nofJobs);
	
}

// bad: -> paging!
@Operation(summary = "Fetch all jobs. ",
		description = "fetches all job entities from the datasource." )
@GetMapping
public ResponseEntity<?> jobs() {
	return ResponseEntity.ok(jobs.findAll()
			.stream()
			.map(JobDto:: fromDomain)
			.toList());
}


@Operation(summary = "Fetch filtered ID.",
description = "fetches all job entities from the datasource matching ID.")
@GetMapping(path = "/{id}")
public ResponseEntity<?> get (@PathVariable long id){
	Optional<JobDto> maybeJob = jobs.findById(id)
			.map(JobDto::fromDomain);
	return ResponseEntity.ok(maybeJob);
}
}

