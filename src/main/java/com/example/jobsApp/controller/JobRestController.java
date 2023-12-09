package com.example.jobsApp.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobsApp.models.Job;
import com.example.jobsApp.repositories.JobRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Job", description = "The Job API")
@RestController
@RequestMapping("/api/jobs")
public class JobRestController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	final JobRepository jobs;

	public JobRestController(JobRepository jobs) {
		log.trace("calling constructor");
		this.jobs = jobs;
	}

	 @PostMapping
	    public ResponseEntity<?> create(@RequestBody JobDto newJobDto) {
	        if (newJobDto.id() != null) {
	            return ResponseEntity.badRequest().body("Id must be null");
	        }

	        Job newJob = new Job(
	                (newJobDto.title() == null) ? "Unknown" : newJobDto.title(),
	                (newJobDto.description() == null) ? "Unknown" : newJobDto.description(),
	                (newJobDto.companyId() == null) ? 1 : newJobDto.companyId(),
	                newJobDto.quantity(),
	                newJobDto.status(),
	                newJobDto.workload()
	        );

	        newJob = jobs.save(newJob);

	        if (newJob == null) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating new Job");
	        } else {
	            return ResponseEntity.status(HttpStatus.CREATED).body(newJob);
	        }
	    }

	@GetMapping("/stat/total")
	public String totalNumberofJobs() {
		long nofJobs = jobs.count();
		log.trace("number of jobs: " + nofJobs);
		return String.valueOf(nofJobs);

	}

	@Operation(summary = "Fetch all jobs. ", description = "fetches all job entities from the datasource.")
	@GetMapping
	public ResponseEntity<?> jobs() {
		return ResponseEntity.ok(jobs.findAll());
	}

	@Operation(summary = "Fetch filtered ID.", description = "fetches all job entities from the datasource matching ID.")
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		log.trace("Search Company by ID: " + id);

		if (!jobs.existsById(id)) {
			return new ResponseEntity<>("Job not found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(jobs.findById(id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable("id") Long id) {
        log.trace("Deleting job by id " + id);

        if (!jobs.existsById(id)) {
            return ((BodyBuilder) ResponseEntity.notFound()).body("Job not found.");
        }

        try {
            jobs.deleteById(id);
            String s = "Deleted: " + id;
            return ResponseEntity.ok(s);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Referential integrity constraint violation.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(@PathVariable long id, @RequestBody JobDto jobDto) {
        if (jobDto.id() != null && jobDto.id() != id) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Path variable of id is not equal to job id");
        }

        Optional<Job> maybeJob = jobs.findById(id);
        if (maybeJob.isEmpty()) {
            return ((BodyBuilder) ResponseEntity.notFound()).body("Unable to update job with given id " + id);
        }

        Job job = maybeJob.get();
        job.setTitle(jobDto.title());
        job.setDescription(jobDto.description());
        job.setCompanyId(jobDto.companyId());
        job.setQuantity(jobDto.quantity());
        job.setStatus(jobDto.status());
        job.setWorkload(jobDto.workload());

        job = jobs.save(job);

        return ResponseEntity.ok(job);
    }
}
