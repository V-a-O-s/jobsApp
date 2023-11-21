package com.example.jobsApp.controller;

import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.jobsApp.models.Company;
import com.example.jobsApp.repositories.CompanyRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SuppressWarnings("unused")
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
	public String totalNumberCompanies() {
		long c = company.count();
		log.trace(c+" Companies");
		return String.valueOf(c);
	}
	
	@GetMapping("/all")
	public List<Company> allCompanies() {
		List<Company> a = company.findAll();
		log.trace("Looked for Companies");
		return a;
	}
	
	@GetMapping(value = "/findById/{id}")
	public Company getCompanyByID(@PathVariable("id") Long id) {
		log.trace("Search Company by ID: "+id);
		return company.findAll().stream().filter(t -> t.getId()==id).toList().get(0);
		//return a.get(0);
		//return ;
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<Company> addNewCompany(@RequestBody Company newComp) {
		log.trace("Creating Company");
		Company comp = company.save(newComp);
		if(comp==null) {
			return new ResponseEntity<>(comp, HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			return new ResponseEntity<>(comp, HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping(value = "/remove/{id}")
	public void removeCompany(@PathVariable("id") Long id){
		
		Company comp = company.findAll().stream().filter(t -> t.getId()==id).toList().get(0);// deleteById(id);
		company.delete(comp);
		//String s = "Deleted "+id;
		//return new ResponseEntity<>(s, HttpStatus.OK); 
	}
	
	
	
	/*
	@Autowired
	private EntityManager em;
	
	
	@GetMapping("/test")
	public String testRes(@RequestParam(value="name", defaultValue="Welt!") String test) {
		return "The Server says\n\"Hallo "+test+"\"";
	}
	
	@GetMapping("/job")
	public List<Jobs> getJobs(
	        @RequestParam(value = "title", defaultValue = "is not null", required = false) String title,
	        @RequestParam(value = "pensum", defaultValue = "is not null", required = false) String pensum,
	        @RequestParam(value = "typ", defaultValue = "is not null", required = false) String type,
	        @RequestParam(value = "sort", defaultValue = "asc", required = false) String sort) {

	    StringBuilder jpqlBuilder = new StringBuilder("SELECT j FROM j WHERE id is not null");

	    jpqlBuilder.append(!title.equals("is not null") ? " AND FROM j.title = :title" : "");
	    jpqlBuilder.append(!pensum.equals("is not null") ? " AND FROM j.pensum = :pensum" : "");
	    jpqlBuilder.append(!type.equals("is not null") ? " AND FROM j.type = :type" : "");

	    jpqlBuilder.append(" ORDER BY j.updated ").append(sort.equals("asc") ? "asc" : "desc");

	    TypedQuery<Jobs> query = em.createQuery(jpqlBuilder.toString(), Jobs.class);

	    query.setParameter("title", !title.equals("is not null") ? title : null);
	    query.setParameter("pensum", !pensum.equals("is not null") ? pensum : null);
	    query.setParameter("type", !type.equals("is not null") ? type : null);

	    return query.getResultList();
	}
	
	
	@GetMapping("/jobs")
	public List<Jobs> getAllJobs(
			@RequestParam(value="pageid",defaultValue="0", required=false) int pageid,
			@RequestParam(value="pagesize",defaultValue="3", required=false) int pagesize){
		
		TypedQuery<Jobs> query = em.createQuery("select j from j",Jobs.class).setFirstResult(pagesize*pageid).setMaxResults(pagesize);
		return query.getResultList();
	}
	//*/
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



