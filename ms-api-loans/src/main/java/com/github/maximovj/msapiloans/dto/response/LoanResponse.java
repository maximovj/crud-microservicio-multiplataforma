package com.github.maximovj.msapiloans.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanResponse {
    private Long loanId;
    private Long userId;
    private BigDecimal amount;
    private BigDecimal apr;
    private BigDecimal totalInterest;
    private BigDecimal monthlyPayment;
    private String status;
    private String rejectionReason;
    private LocalDateTime applicationDate;
    private LocalDateTime updatedAt;   
}
