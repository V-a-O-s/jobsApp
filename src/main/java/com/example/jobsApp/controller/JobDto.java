package com.example.jobsApp.controller;

import com.example.jobsApp.models.Job;

record JobDto(
		Long id,
		String title,
		String description,
		String company_id,
		String anzahl,
		String status,
		String pensum) {
	
	public static JobDto fromDomain(Job a) {
		return new JobDto(
			a.getId(),
			a.getTitle(),
		    a.getDescription(),
		    a.getCompany_id(),
		    a.getAnzahl(),
		    a.getStatus(),
		    a.getPensum()
		);
	}
}