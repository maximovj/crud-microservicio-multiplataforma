package com.github.maximovj.msapiloans.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.github.maximovj.msapiloans.dto.request.LoanRequest;
import com.github.maximovj.msapiloans.dto.response.LoanResponse;
import com.github.maximovj.msapiloans.entities.Loan;
import com.github.maximovj.msapiloans.mapper.LoanMapper;
import com.github.maximovj.msapiloans.mapper.LoanMapperExtended;
import com.github.maximovj.msapiloans.repository.ILoanRepository;

@Service
public class LoanServiceImpl implements ILoanService {

    @Autowired
    @Qualifier("jsonLoans")
    private ILoanRepository loanRepository;

    @Autowired
    private LoanMapperExtended mapperExtended;

    @Autowired
    private LoanMapper mapper;

    @Override
    public List<LoanResponse> obtenerTodos() {
        List<Loan> loans = loanRepository.obtenerTodos();
        return mapperExtended.toSummaryDTOList(loans);
    }

    @Override
    public LoanResponse buscarPorId(Long id) {
        return mapperExtended.toSummaryDTO(loanRepository.buscarPorId(id));
    }

    @Override
    public LoanResponse crear(LoanRequest request) {
        Loan loan = loanRepository.crear(request);
        return mapper.toResponseDTO(loan);
    }

    @Override
    public LoanResponse actualizar(Long id, LoanRequest request) {
        Loan loan = loanRepository.actualizar(id, request);
        return mapperExtended.toSummaryDTO(loan);
    }

    @Override
    public void eliminar(Long id) {

    }
    
}
