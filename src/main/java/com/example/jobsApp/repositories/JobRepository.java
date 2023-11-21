package com.example.jobsApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobsApp.models.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

}
