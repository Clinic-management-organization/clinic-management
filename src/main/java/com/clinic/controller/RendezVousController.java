package com.clinic.controller;

import com.clinic.entity.Consultation;
import com.clinic.entity.RendezVous;
import com.clinic.entity.Enum.EtatRDV;
import com.clinic.exception.NotFoundException;
import com.clinic.metier.RendezVousMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/rendezvous")
public class RendezVousController {

    private final RendezVousMetier rendezVousMetier;

    @Autowired
    public RendezVousController(RendezVousMetier rendezVousMetier) {
        this.rendezVousMetier = rendezVousMetier;
    }

    @GetMapping
    public ResponseEntity<List<RendezVous>> getAllRendezVous() {
        List<RendezVous> rendezVousList = rendezVousMetier.getAllRendezVous();
        return new ResponseEntity<>(rendezVousList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RendezVous> getRendezVousById(@PathVariable Long id) throws NotFoundException {
        RendezVous rendezVous = rendezVousMetier.getRendezVousById(id);
        return new ResponseEntity<>(rendezVous, HttpStatus.OK);
    }
    @GetMapping("/medecin/{medecinId}")
    public List<RendezVous> getRendezVousByMedecin(@PathVariable Long medecinId) {
        return rendezVousMetier.getRendezVousByMedecin(medecinId);
    }
    
    @GetMapping("/patient/{patientId}")
    public List<RendezVous> getRendezVousByPatient(@PathVariable Long patientId) {
        return rendezVousMetier.getRendezVousByPatient(patientId);
    } 
    @PostMapping
    public ResponseEntity<RendezVous> saveRendezVous(@RequestBody RendezVous rendezVous) {
        RendezVous savedRendezVous = rendezVousMetier.saveRendezVous(rendezVous);
        return new ResponseEntity<>(savedRendezVous, HttpStatus.CREATED);
    }
    @PostMapping("/add-to-dossier/{dossierId}")
    public ResponseEntity<RendezVous> addRendezVousToDossier(
    		@PathVariable Long dossierId,
            @RequestBody RendezVous rendezVous) {
        RendezVous savedRDV = rendezVousMetier.addRendezVousToDossier(dossierId, rendezVous);
        return new ResponseEntity<>(savedRDV, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RendezVous> updateRendezVous(@PathVariable Long id, @RequestBody RendezVous rendezVous) throws NotFoundException {
        rendezVous.setId(id);
        RendezVous updatedRendezVous = rendezVousMetier.updateRendezVous(rendezVous);
        return new ResponseEntity<>(updatedRendezVous, HttpStatus.OK);
    }

    
    @PutMapping("/updateEtat/{id}")
    public ResponseEntity<RendezVous> updateEtatRendezVous(@PathVariable Long id, @RequestBody EtatRDV newEtat) throws NotFoundException {
        RendezVous existingRendezVous = rendezVousMetier.getRendezVousById(id);

        existingRendezVous.setEtatRendezVous(newEtat);

        RendezVous updatedRendezVous = rendezVousMetier.updateRendezVous(existingRendezVous);
        return new ResponseEntity<>(updatedRendezVous, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRendezVous(@PathVariable Long id) throws NotFoundException {
        rendezVousMetier.deleteRendezVous(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
