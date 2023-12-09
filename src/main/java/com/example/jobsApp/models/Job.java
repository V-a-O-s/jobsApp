package com.example.jobsApp.models;

import java.sql.Timestamp;
import java.util.Objects;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name = "jobs")
public class Job {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	private String title;
	@Column(nullable = false)
	private String description;
	@Column(name = "companyId")
	private Long companyId;
	@Column(name = "anzahl")
	private Integer quantity;
	@Column(nullable = false)
	private String status;
	@Column(nullable = false, name = "pensum")
	private String workload;
	private Timestamp updated;
		
	protected Job() {}
	
	public Job(String title, String description, Long companyId, Integer quantity, String status, String workload) {
	    setTitle(title);
	    setDescription(description);
	    setCompanyId(companyId);
	    setQuantity((quantity != null) ? quantity : 1);
	    setStatus((status != null) ? status : "Job");
	    setWorkload((workload != null) ? workload : "100");
	    setUpdated(new Timestamp(System.currentTimeMillis()));
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public String getWorkload() {
		return workload;
	}

	public void setStatus(String status) {
		//*
		switch(status) {
			case "Lehrstelle":
				this.status = status;
				break;
			case "Praktikum":
				this.status = status;
				break;
			default:
				this.status= "Job";
		}
		//*/this.status = status;
	}
	public void setWorkload(String workload) {
		//*
		switch(workload) {
			case "20":
				this.workload = workload;
				break;
			case "40":
				this.workload = workload;
				break;
			case "60":
				this.workload = workload;
				break;
			case "80":
				this.workload = workload;
				break;
			default:
				this.workload = "100";
		}
		//*/this.workload = workload;
    }
	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Job other = (Job) obj;
		return id == other.id && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", title=" + title + ", description=" + description + ", companyId=" + companyId
				+ ", quantity=" + quantity + ", status=" + status + ", workload=" + workload + ", updated=" + updated + "]";
	}
	
	
}
