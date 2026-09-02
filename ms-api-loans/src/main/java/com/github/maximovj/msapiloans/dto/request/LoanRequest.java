package com.github.maximovj.msapiloans.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanRequest {
    private Long userId;
    private BigDecimal amount;
    private BigDecimal income;
    private Integer loanTerm;
    private String loanType;
    private String email;
    private String phoneNumber;
    private BigDecimal apr;
    private BigDecimal totalInterest;
    private BigDecimal monthlyPayment;
    private String status;
    private String rejectionReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
