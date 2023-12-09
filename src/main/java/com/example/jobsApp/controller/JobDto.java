package com.example.jobsApp.controller;

import com.example.jobsApp.models.Job;

record JobDto(
        Long id,
        String title,
        String description,
        Long companyId,
        Integer quantity,
        String status,
        String workload) {

    public static JobDto fromDomain(Job job) {
        return new JobDto(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getCompanyId(),
                job.getQuantity(),
                job.getStatus(),
                job.getWorkload()
        );
    }
}
