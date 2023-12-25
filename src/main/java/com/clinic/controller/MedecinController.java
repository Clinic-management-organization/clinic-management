package com.clinic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.clinic.entity.Medecin;
import com.clinic.exception.NotFoundException;
import com.clinic.metier.MedecinMetier;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/medecins")
public class MedecinController {

    private final MedecinMetier medecinMetier;

    @Autowired
    public MedecinController(MedecinMetier medecinMetier) {
        this.medecinMetier = medecinMetier;
    }

    @GetMapping
    public ResponseEntity<List<Medecin>> getAllMedecins() {
        List<Medecin> medecins = medecinMetier.getAllMedecins();
        return new ResponseEntity<>(medecins, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medecin> getMedecinById(@PathVariable Long id) throws NotFoundException {
        Medecin medecin = medecinMetier.getMedecinById(id);
        return new ResponseEntity<>(medecin, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Medecin> saveMedecin(@RequestBody Medecin medecin) {
        Medecin savedMedecin = medecinMetier.saveMedecin(medecin);
        return new ResponseEntity<>(savedMedecin, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Medecin> updateMedecin(@PathVariable Long id, @RequestBody Medecin medecin) throws NotFoundException {
        medecin.setId(id);
        Medecin updatedMedecin = medecinMetier.updateMedecin(medecin);
        return new ResponseEntity<>(updatedMedecin, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteMedecin(@PathVariable Long id) throws NotFoundException {
        medecinMetier.deleteMedecin(id);
        return new ResponseEntity<>("Medecin with id " + id + " has been deleted", HttpStatus.OK);
    }
}

