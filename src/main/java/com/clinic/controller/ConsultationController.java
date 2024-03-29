package com.clinic.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.clinic.entity.Consultation;
import com.clinic.exception.NotFoundException;
import com.clinic.metier.ConsultationMetier;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/consultations")
public class ConsultationController {

    private final ConsultationMetier consultationMetier;

    @Autowired
    public ConsultationController(ConsultationMetier consultationMetier) {
        this.consultationMetier = consultationMetier;
    }

    // Endpoint pour obtenir la liste de toutes les consultations
    @GetMapping
    public ResponseEntity<List<Consultation>> getAllConsultations() {
        List<Consultation> consultations = consultationMetier.getAllConsultations();
        return new ResponseEntity<>(consultations, HttpStatus.OK);
    }

    // Endpoint pour obtenir une consultation par son identifiant
    @GetMapping("/{id}")
    public ResponseEntity<Consultation> getConsultationById(@PathVariable Long id) throws NotFoundException {
        Consultation consultation = consultationMetier.getConsultationById(id);
        return new ResponseEntity<>(consultation, HttpStatus.OK);
    }

    // Endpoint pour enregistrer une nouvelle consultation
    @PostMapping
    public ResponseEntity<Consultation> saveConsultation(@RequestBody Consultation consultation) {
        Consultation savedConsultation = consultationMetier.saveConsultation(consultation);
        return new ResponseEntity<>(savedConsultation, HttpStatus.CREATED);
    }

    // Endpoint pour ajouter une consultation à un dossier médical
    @PostMapping("/add-to-dossier/{dossierId}")
    public ResponseEntity<Consultation> addConsultationToDossier(
            @PathVariable Long dossierId,
            @RequestBody Consultation consultation) {
        Consultation savedConsultation = consultationMetier.addConsultationToDossier(dossierId, consultation);
        return new ResponseEntity<>(savedConsultation, HttpStatus.CREATED);
    }

    // Endpoint pour mettre à jour une consultation existante
    @PutMapping("/{id}")
    public ResponseEntity<Consultation> updateConsultation(@PathVariable Long id, @RequestBody Consultation consultation) throws NotFoundException {
        consultation.setId(id);
        Consultation updatedConsultation = consultationMetier.updateConsultation(consultation);
        return new ResponseEntity<>(updatedConsultation, HttpStatus.OK);
    }

    // Endpoint pour supprimer une consultation par son identifiant
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteConsultation(@PathVariable Long id) throws NotFoundException {
        consultationMetier.deleteConsultation(id);
        return new ResponseEntity<>("Consultation with id " + id + " has been deleted", HttpStatus.OK);
    }

    // Endpoint pour obtenir le revenu total de toutes les consultations
    @GetMapping("/income")
    public ResponseEntity<BigDecimal> getTotalIncome() {
        BigDecimal totalIncome = consultationMetier.getTotalIncome();
        return new ResponseEntity<>(totalIncome, HttpStatus.OK);
    }

    // Endpoint pour obtenir le revenu mensuel pour une année et un mois spécifiques
    @GetMapping("/income/{year}/{month}")
    public ResponseEntity<BigDecimal> getMonthlyIncome(@PathVariable int year, @PathVariable int month) {
        BigDecimal monthlyIncome = consultationMetier.getMonthlyIncome(year, month);
        return new ResponseEntity<>(monthlyIncome, HttpStatus.OK);
    }

    // Endpoint pour obtenir le revenu mensuel pour chaque mois de l'année
    @GetMapping("/income/year")
    public ResponseEntity<List<Object[]>> getMonthly() {
        List<Object[]> monthlyIncome = consultationMetier.getBymonth();
        return new ResponseEntity<>(monthlyIncome, HttpStatus.OK);
    }
}
