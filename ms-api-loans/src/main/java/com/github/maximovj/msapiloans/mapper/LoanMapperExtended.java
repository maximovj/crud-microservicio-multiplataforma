package com.github.maximovj.msapiloans.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.github.maximovj.msapiloans.dto.response.LoanResponse;
import com.github.maximovj.msapiloans.entities.Loan;

import java.util.List;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface LoanMapperExtended {
    
    // Resumen para listados
    @Mapping(target = "id", source = "id")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "createdAt", source = "createdAt")
    LoanResponse toSummaryDTO(Loan loan);
    
    // Lista de resúmenes
    List<LoanResponse> toSummaryDTOList(List<Loan> loans);

    /** 
    // Mapeo de Payment a PaymentResponseDTO
    @Mapping(target = "success", constant = "true")
    @Mapping(target = "paymentAmount", source = "amount")
    @Mapping(target = "remainingBalance", source = "loan.amount")
    PaymentResponseDTO toPaymentResponseDTO(Payment payment);
    **/
}