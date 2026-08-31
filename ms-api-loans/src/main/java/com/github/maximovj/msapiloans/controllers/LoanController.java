package com.github.maximovj.msapiloans.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.msapiloans.dto.request.LoanRequest;
import com.github.maximovj.msapiloans.dto.response.LoanResponse;
import com.github.maximovj.msapiloans.services.ILoanService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@RequestMapping("/api/loans")
@RestController()
@CrossOrigin(origins = "*", methods = {
    RequestMethod.GET,
    RequestMethod.POST, 
    RequestMethod.PATCH, 
    RequestMethod.PUT, 
    RequestMethod.DELETE 
})
public class LoanController {

    @Autowired
    ILoanService loanService;

    @GetMapping()
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(loanService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.buscarPorId(id));
    }
    
    @PostMapping()
    public ResponseEntity<?> crear(@RequestBody LoanRequest request) {
        return ResponseEntity.ok(loanService.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody LoanRequest request) {
        return ResponseEntity.ok(loanService.actualizar(id, request));
    }
   
    @DeleteMapping("/{id}") 
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        loanService.eliminar(id);
        return ResponseEntity.ok(null);
    }
}
