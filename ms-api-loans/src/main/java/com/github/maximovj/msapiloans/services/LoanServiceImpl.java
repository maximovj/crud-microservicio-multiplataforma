package com.github.maximovj.msapiloans.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.maximovj.msapiloans.dto.request.LoanRequest;
import com.github.maximovj.msapiloans.dto.response.LoanResponse;
import com.github.maximovj.msapiloans.entities.Loan;
import com.github.maximovj.msapiloans.mapper.LoanMapper;
import com.github.maximovj.msapiloans.mapper.LoanMapperExtended;
import com.github.maximovj.msapiloans.repository.ILoanJsonRepository;
import com.github.maximovj.msapiloans.repository.LoanRepository;

@Service
public class LoanServiceImpl implements ILoanService {

    @Autowired @Qualifier("jsonLoans") private ILoanJsonRepository loanJsonRepository;
    @Autowired private LoanRepository loanRepository;
    @Autowired private LoanMapperExtended mapperExtended;
    @Autowired private LoanMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<LoanResponse> obtenerTodos() {
        List<Loan> loans = loanRepository.findAll();
        return mapperExtended.toSummaryDTOList(loans);
    }

    @Override
    @Transactional(readOnly = true)
    public LoanResponse buscarPorId(Long id) {
        Optional<Loan> loan = loanRepository.findById(id);
        if(loan.isPresent()) {
            return mapperExtended.toSummaryDTO(loan.get());

        }
        return mapperExtended.toSummaryDTO(null);
    }

    @Override
    @Transactional
    public LoanResponse crear(LoanRequest request) {
        Loan loan = loanRepository.save(mapper.toEntity(request));
        return mapper.toResponseDTO(loan);
    }

    @Override
    @Transactional
    public LoanResponse actualizar(Long id, LoanRequest request) {
        Optional<Loan> loan = loanRepository.findById(id);
        if(loan.isPresent()) {
            mapper.updateEntity(loan.get(), request);
            loanRepository.save(loan.get());
        } 
        return mapperExtended.toSummaryDTO(loan.get());
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        loanRepository.delete(loanRepository.findById(id).orElse(null));
    }
    
}
