package com.clinic.controller;

import com.clinic.entity.Traitement;
import com.clinic.metier.TraitementMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/traitements")
public class TraitementController {

    private final TraitementMetier traitementMetier;

    @Autowired
    public TraitementController(TraitementMetier traitementMetier) {
        this.traitementMetier = traitementMetier;
    }

    @PostMapping
    public ResponseEntity<Traitement> saveTraitement(@RequestBody Traitement traitement) {
        Traitement savedTraitement = traitementMetier.saveTraitement(traitement);
        return new ResponseEntity<>(savedTraitement, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Traitement> getTraitementById(@PathVariable Long id) {
        Traitement traitement = traitementMetier.getTraitementById(id);
        return traitement != null ?
                new ResponseEntity<>(traitement, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Traitement>> getAllTraitements() {
        List<Traitement> traitements = traitementMetier.getAllTraitements();
        return new ResponseEntity<>(traitements, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Traitement> updateTraitement(@PathVariable Long id,@RequestBody Traitement traitement) {
        Traitement updatedTraitement = traitementMetier.updateTraitement(traitement);
        return updatedTraitement != null ?
                new ResponseEntity<>(updatedTraitement, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTraitement(@PathVariable Long id) {
        traitementMetier.deleteTraitement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
