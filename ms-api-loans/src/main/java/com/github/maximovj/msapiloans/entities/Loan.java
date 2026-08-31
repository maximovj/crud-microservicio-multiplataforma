package com.github.maximovj.msapiloans.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import lombok.Data;

@Data
public class Loan {
    private Long loanId;
    private Long userId;
    private BigDecimal amount;
    private BigDecimal apr;
    private BigDecimal totalInterest;
    private BigDecimal monthlyPayment;
    private String status;
    private String rejectionReason;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime applicationDate;
    
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updatedAt;
}
