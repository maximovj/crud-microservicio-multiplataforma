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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "Préstamos", description = "API para la gestión de préstamos")
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

    @Operation(summary = "Obtener todos los préstamos", 
               description = "Retorna una lista de todos los préstamos registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Préstamos encontrados"),
        @ApiResponse(responseCode = "404", description = "No se encontraron préstamos", 
                     content = @Content)
    })
    @GetMapping()
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(loanService.obtenerTodos());
    }

    @Operation(summary = "Obtener un préstamo por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Préstamo encontrado"),
        @ApiResponse(responseCode = "404", description = "Préstamo no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.buscarPorId(id));
    }
    
    @Operation(summary = "Crear un nuevo préstamo")
    @ApiResponse(responseCode = "201", description = "Préstamo creado exitosamente")
    @PostMapping()
    public ResponseEntity<?> crear(@RequestBody LoanRequest request) {
        return ResponseEntity.ok(loanService.crear(request));
    }

    @Operation(summary = "Actualizar un préstamo")
    @ApiResponse(responseCode = "200", description = "Préstamo actualizado exitosamente")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody LoanRequest request) {
        return ResponseEntity.ok(loanService.actualizar(id, request));
    }
   
    @Operation(summary = "Eliminar un préstamo")
    @ApiResponse(responseCode = "200", description = "Préstamo eliminado exitosamente")
    @DeleteMapping("/{id}") 
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        loanService.eliminar(id);
        return ResponseEntity.ok(null);
    }
}
