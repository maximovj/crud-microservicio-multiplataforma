package com.github.maximovj.msapiloans.repository;

import java.util.List;

import com.github.maximovj.msapiloans.dto.request.LoanRequest;
import com.github.maximovj.msapiloans.entities.Loan;

public interface ILoanRepository {
    List<Loan> obtenerTodos();
    Loan buscarPorId(Long id);
    Loan crear(LoanRequest request);
    Loan actualizar(Long id, LoanRequest request);
    void eliminar(Long id);
}
