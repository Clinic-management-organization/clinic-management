package com.clinic.controller;

import com.clinic.entity.Diagnostic;
import com.clinic.metier.DiagnosticMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/diagnostics")
public class DiagnosticController {

    // Déclaration d'une instance de DiagnosticMetier pour gérer les opérations métier des diagnostics
    private final DiagnosticMetier diagnosticMetier;

    @Autowired
    public DiagnosticController(DiagnosticMetier diagnosticMetier) {
        this.diagnosticMetier = diagnosticMetier;
    }

    // Endpoint pour enregistrer un nouveau diagnostic
    @PostMapping
    public ResponseEntity<Diagnostic> saveDiagnostic(@RequestBody Diagnostic diagnostic) {
        Diagnostic savedDiagnostic = diagnosticMetier.saveDiagnostic(diagnostic);
        return new ResponseEntity<>(savedDiagnostic, HttpStatus.CREATED);
    }

    // Endpoint pour obtenir un diagnostic par son identifiant
    @GetMapping("/{id}")
    public ResponseEntity<Diagnostic> getDiagnosticById(@PathVariable Long id) {
        Diagnostic diagnostic = diagnosticMetier.getDiagnosticById(id);
        return diagnostic != null ?
                new ResponseEntity<>(diagnostic, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint pour obtenir la liste de tous les diagnostics
    @GetMapping
    public ResponseEntity<List<Diagnostic>> getAllDiagnostics() {
        List<Diagnostic> diagnostics = diagnosticMetier.getAllDiagnostics();
        return new ResponseEntity<>(diagnostics, HttpStatus.OK);
    }

    // Endpoint pour mettre à jour un diagnostic existant
    @PutMapping("/update/{id}")
    public ResponseEntity<Diagnostic> updateDiagnostic(@PathVariable Long id, @RequestBody Diagnostic diagnostic) {
        Diagnostic updatedDiagnostic = diagnosticMetier.updateDiagnostic(diagnostic);
        return updatedDiagnostic != null ?
                new ResponseEntity<>(updatedDiagnostic, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint pour supprimer un diagnostic par son identifiant
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDiagnostic(@PathVariable Long id) {
        diagnosticMetier.deleteDiagnostic(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
