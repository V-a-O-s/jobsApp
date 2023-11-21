package com.example.jobsApp.controller;

import com.example.jobsApp.models.Company;

record CompanyDto(
		long id,
		String name,
		String plz,
		String ort) {		
	public static CompanyDto fromDomain(Company c) {
		return new CompanyDto(
			c.getId(),
			c.getName(),
			c.getPlz(),
			c.getOrt()
		);
	}
}