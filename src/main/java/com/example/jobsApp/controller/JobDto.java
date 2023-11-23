package com.example.jobsApp.controller;

import java.sql.Timestamp;

import com.example.jobsApp.models.Job;

record JobDto(
		  long id,
		  String title,
		  String description,
		  long company_id,
		  int anzahl,
		  String status,
		  String pensum,
		  Timestamp updated) {

	  public static JobDto fromDomain(Job a) {
		    return new JobDto(a.getId(),
		        a.getTitle(),
		        a.getDescription(),
		        a.getCompany_id(),
		        a.getAnzahl(),
		        a.getStatus(),
		        a.getPensum(),
		        a.getUpdated()
		    		);
	  }
}
