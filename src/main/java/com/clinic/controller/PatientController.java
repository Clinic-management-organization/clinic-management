package com.clinic.controller;

import com.clinic.entity.Patient;
import com.clinic.entity.Enum.GenderType;
import com.clinic.exception.NotFoundException;
import com.clinic.metier.PatientMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/patients")
public class PatientController {

    // Déclaration d'une instance de PatientMetier pour gérer les opérations métier
    private final PatientMetier patientMetier;

    @Autowired
    public PatientController(PatientMetier patientMetier) {
        this.patientMetier = patientMetier;
    }

    // Endpoint pour enregistrer un nouveau patient
    @PostMapping
    public ResponseEntity<Patient> savePatient(@RequestBody Patient patient) {
        Patient savedPatient = patientMetier.savePatient(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    // Endpoint pour obtenir un patient par son identifiant
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        try {
            Patient patient = patientMetier.getPatientById(id);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour obtenir la liste de tous les patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientMetier.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // Endpoint pour mettre à jour un patient existant
    @PutMapping("/update/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        try {
            patient.setId(id);
            Patient updatedPatient = patientMetier.updatePatient(patient);
            return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour supprimer un patient par son identifiant
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        try {
            patientMetier.deletePatient(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour rechercher des patients en fonction de certains critères
    @GetMapping("/search")
    public List<Patient> searchPatients(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String prenom,
            @RequestParam(required = false) String tel,
            @RequestParam(required = false) GenderType sexe) {
        // Si aucun paramètre n'est fourni, retourne tous les patients
        if (nom == null && prenom == null && tel == null && sexe == null) {
            return patientMetier.getAllPatients();
        }
        // Appeler la méthode de service avec les paramètres de recherche
        return patientMetier.searchPatients(nom, prenom, tel, sexe);
    }
}
