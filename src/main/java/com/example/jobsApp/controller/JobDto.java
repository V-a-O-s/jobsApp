package com.example.jobsApp.controller;

import com.example.jobsApp.models.Job;

record JobDto(Long id, String title) {

	public static JobDto fromDomain(Job a) {
		return new JobDto(a.getId(), a.getTitle());
	}

}
