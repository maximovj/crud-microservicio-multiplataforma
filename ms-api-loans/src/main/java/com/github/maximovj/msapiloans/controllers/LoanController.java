package com.github.maximovj.msapiloans.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.msapiloans.dto.request.LoanRequest;
import com.github.maximovj.msapiloans.dto.response.LoanResponse;

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
    @GetMapping()
    public ResponseEntity<?> listarTodos() {
        List<LoanResponse> loans = new ArrayList<>();
        loans.add(new LoanResponse());
        loans.add(new LoanResponse());
        loans.add(new LoanResponse());
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(new LoanResponse());
    }
    
    @PostMapping()
    public ResponseEntity<?> crear(@RequestBody LoanRequest request) {
        return ResponseEntity.ok(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody LoanRequest request) {
        return ResponseEntity.ok(request);
    }
   
    @DeleteMapping("/{id}") 
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        return ResponseEntity.ok(null);
    }
}
