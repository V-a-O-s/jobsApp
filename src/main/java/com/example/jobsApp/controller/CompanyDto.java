package com.example.jobsApp.controller;

import com.example.jobsApp.models.Company;

record CompanyDto(
		Long id,
		String name,
		String logo_url,
		String address,
		String plz,
		String ort,
		String website) {	
	
	public static CompanyDto fromDomain(Company c) {
		return new CompanyDto(
			c.getId(),
			c.getName(),
			c.getLogoUrl(),
			c.getAddress(),
			c.getPlz(),
			c.getOrt(),
			c.getWebsite()
		);
	}
}