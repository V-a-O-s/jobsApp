package com.example.jobsApp.models;

import java.sql.Timestamp;
import java.util.Objects;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
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
	private String description;
	private String company_id;
	private String anzahl;
	private String status;
	private String pensum;
	private Timestamp updated;
		
	protected Job() {}
	
	public Job(String title, String description, String company_id, String anzahl, String status, String pensum) {
	    setTitle(title);
	    setDescription(description);
	    setCompany_id(company_id);
	    setAnzahl(anzahl);
	    setStatus(status);
	    setPensum(pensum);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getAnzahl() {
		return anzahl;
	}
	public void setAnzahl(String anzahl) {
		this.anzahl = anzahl;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setPensum(String pensum) {
		switch(pensum) {
			case "20":
				this.pensum = pensum;
				break;
			case "40":
				this.pensum = pensum;
				break;
			case "60":
				this.pensum = pensum;
				break;
			case "80":
				this.pensum = pensum;
				break;
			default:
				this.pensum = "100";
		}
		
		this.pensum = pensum;
    }
	public String getPensum() {
		return pensum;
	}
	
	public Timestamp getUpdated() {
		return updated;
	}
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}


	


	@Override
	public int hashCode() {
		return Objects.hash(title);
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
		return Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return status+" as a "+title+" at "+ pensum + "%";
	}//*/
}
