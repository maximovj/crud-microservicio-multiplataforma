package com.github.maximovj.msapiloans.mapper;

import org.mapstruct.*;

import com.github.maximovj.msapiloans.dto.request.LoanRequest;
import com.github.maximovj.msapiloans.dto.response.LoanResponse;
import com.github.maximovj.msapiloans.entities.Loan;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface LoanMapper {
    
    // Mapeo de DTO Request a Entidad
    @Mapping(target = "loanId")
    @Mapping(target = "apr")
    @Mapping(target = "totalInterest")
    @Mapping(target = "monthlyPayment")
    @Mapping(target = "status")
    @Mapping(target = "rejectionReason")
    @Mapping(target = "applicationDate")
    @Mapping(target = "updatedAt")
    Loan toEntity(LoanRequest requestDTO);
    
    // Mapeo de Entidad a Response DTO
    @Mapping(target = "loanId", source = "loanId")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "applicationDate", source = "applicationDate")
    LoanResponse toResponseDTO(Loan loan);
    
    // Actualización parcial (para updates)
    @Mapping(target = "loanId", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "applicationDate", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(@MappingTarget Loan loan, LoanRequest requestDTO);

    // Método con cálculos personalizados
    default void calculateLoanDetails(Loan loan) {
        // Aquí iría la lógica de cálculo
        // Por ejemplo, si tienes un service que calcula APR
    }
}