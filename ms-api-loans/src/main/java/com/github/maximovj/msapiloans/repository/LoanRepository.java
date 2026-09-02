package com.github.maximovj.msapiloans.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.maximovj.msapiloans.entities.Loan;

@Primary
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    
}