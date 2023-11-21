package com.example.jobsApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobsApp.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
