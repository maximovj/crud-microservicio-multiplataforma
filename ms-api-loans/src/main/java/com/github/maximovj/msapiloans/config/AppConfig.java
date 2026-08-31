package com.github.maximovj.msapiloans.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.github.maximovj.msapiloans.repository.ILoanRepository;
import com.github.maximovj.msapiloans.repository.LoanJsonRepository;

@Configuration
public class AppConfig {
    @Bean
    @Primary
    public ILoanRepository loanRepositoryJson() {
        return new LoanJsonRepository();
    }
}
