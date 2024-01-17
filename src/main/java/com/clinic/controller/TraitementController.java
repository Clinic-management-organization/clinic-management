package com.clinic.controller;

import com.clinic.entity.Traitement;
import com.clinic.metier.TraitementMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/traitements")
public class TraitementController {

    // Injection de dépendance du TraitementMetier
    private final TraitementMetier traitementMetier;

    @Autowired
    public TraitementController(TraitementMetier traitementMetier) {
        this.traitementMetier = traitementMetier;
    }

    // Endpoint pour enregistrer un nouveau traitement
    @PostMapping
    public ResponseEntity<Traitement> saveTraitement(@RequestBody Traitement traitement) {
        Traitement savedTraitement = traitementMetier.saveTraitement(traitement);
        return new ResponseEntity<>(savedTraitement, HttpStatus.CREATED);
    }

    // Endpoint pour obtenir un traitement par son identifiant
    @GetMapping("/{id}")
    public ResponseEntity<Traitement> getTraitementById(@PathVariable Long id) {
        Traitement traitement = traitementMetier.getTraitementById(id);
        return traitement != null ?
                new ResponseEntity<>(traitement, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint pour obtenir la liste de tous les traitements
    @GetMapping
    public ResponseEntity<List<Traitement>> getAllTraitements() {
        List<Traitement> traitements = traitementMetier.getAllTraitements();
        return new ResponseEntity<>(traitements, HttpStatus.OK);
    }

    // Endpoint pour mettre à jour un traitement existant
    @PutMapping("/update/{id}")
    public ResponseEntity<Traitement> updateTraitement(@PathVariable Long id, @RequestBody Traitement traitement) {
        Traitement updatedTraitement = traitementMetier.updateTraitement(traitement);
        return updatedTraitement != null ?
                new ResponseEntity<>(updatedTraitement, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint pour supprimer un traitement par son identifiant
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTraitement(@PathVariable Long id) {
        traitementMetier.deleteTraitement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
