package com.github.maximovj.msapiloans.repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.maximovj.msapiloans.dto.request.LoanRequest;
import com.github.maximovj.msapiloans.entities.Loan;
import com.github.maximovj.msapiloans.mapper.LoanMapper;

@Primary
@Repository("jsonLoans")
public class LoanJsonRepository  implements ILoanRepository {

    @Autowired
    private LoanMapper mapper;

    private List<Loan> list;

    public LoanJsonRepository() {
        Resource resource = new ClassPathResource("json/loans.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            list = Arrays.asList(mapper.readValue(resource.getFile(), Loan[].class));
        } catch (StreamReadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DatabindException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public List<Loan> obtenerTodos() {
        return list;
    }

    @Override
    public Loan buscarPorId(Long id) {
        return list.stream().filter(loan -> loan.getId().equals(id)).findFirst().orElseThrow();
    }

    @Override
    public Loan crear(LoanRequest request) {
        return mapper.toEntity(request);
    }

    @Override
    public Loan actualizar(Long id, LoanRequest request) {
        Loan loan = list.stream().filter(l -> l.getId().equals(id)).findFirst().orElseThrow();
        return mapper.toEntity(request);
    }

    @Override
    public void eliminar(Long id) {
        // TODO: Eliminar
    }

}
