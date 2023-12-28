package com.clinic.controller;

import com.clinic.entity.DossierMedical;
import com.clinic.exception.NotFoundException;
import com.clinic.metier.DossierMedicalMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dossiersMedicaux")
@CrossOrigin(origins = "http://localhost:3000")
public class DossierMedicalController {

    private final DossierMedicalMetier dossierMedicalMetier;

    @Autowired
    public DossierMedicalController(DossierMedicalMetier dossierMedicalMetier) {
        this.dossierMedicalMetier = dossierMedicalMetier;
    }

    @GetMapping
    public ResponseEntity<List<DossierMedical>> getAllDossiersMedicals() {
        List<DossierMedical> dossiersMedicals = dossierMedicalMetier.getAllDossiersMedicals();
        return new ResponseEntity<>(dossiersMedicals, HttpStatus.OK);
    }

    @GetMapping("/patient/{patientId}")
       public List<DossierMedical> getDossiersMedicauxByPatientId(@PathVariable Long patientId) {
           return dossierMedicalMetier.getDossiersMedicauxByPatientId(patientId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DossierMedical> getDossierMedicalById(@PathVariable Long id) throws NotFoundException {
        DossierMedical dossierMedical = dossierMedicalMetier.getDossierMedicalById(id);
        return new ResponseEntity<>(dossierMedical, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DossierMedical> saveDossierMedical(@RequestBody DossierMedical dossierMedical) {
        DossierMedical savedDossierMedical = dossierMedicalMetier.saveDossierMedical(dossierMedical);
        return new ResponseEntity<>(savedDossierMedical, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DossierMedical> updateDossierMedical(@PathVariable Long id, @RequestBody DossierMedical dossierMedical) throws NotFoundException {
        dossierMedical.setId(id);
        DossierMedical updatedDossierMedical = dossierMedicalMetier.updateDossierMedical(dossierMedical);
        return new ResponseEntity<>(updatedDossierMedical, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDossierMedical(@PathVariable Long id) throws NotFoundException {
        dossierMedicalMetier.deleteDossierMedical(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
