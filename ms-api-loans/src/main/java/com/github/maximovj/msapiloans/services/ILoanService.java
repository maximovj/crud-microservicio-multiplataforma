package com.github.maximovj.msapiloans.services;

import java.util.List;

import com.github.maximovj.msapiloans.dto.request.LoanRequest;
import com.github.maximovj.msapiloans.dto.response.LoanResponse;

public interface ILoanService {
    List<LoanResponse> obtenerTodos();
    LoanResponse buscarPorId(Long id);
    LoanResponse crear(LoanRequest request);
    LoanResponse actualizar(Long id, LoanRequest request);
    void eliminar(Long id);
}
