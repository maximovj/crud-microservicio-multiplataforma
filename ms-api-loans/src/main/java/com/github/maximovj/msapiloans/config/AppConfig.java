package com.github.maximovj.msapiloans.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.github.maximovj.msapiloans.repository.ILoanJsonRepository;
import com.github.maximovj.msapiloans.repository.LoanJsonRepository;

@Configuration
public class AppConfig {
    @Bean
    @Primary
    public ILoanJsonRepository loanRepositoryJson() {
        return new LoanJsonRepository();
    }
}
