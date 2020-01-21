package com.playground.employee;

import org.springframework.data.jpa.repository.JpaRepository;

interface CompanyRepository extends JpaRepository<Company, Long> {

}