package com.clinic.controller;

import com.clinic.entity.RendezVous;
import com.clinic.exception.NotFoundException;
import com.clinic.metier.RendezVousMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/rendez-vous")
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

    @PostMapping
    public ResponseEntity<RendezVous> saveRendezVous(@RequestBody RendezVous rendezVous) {
        RendezVous savedRendezVous = rendezVousMetier.saveRendezVous(rendezVous);
        return new ResponseEntity<>(savedRendezVous, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RendezVous> updateRendezVous(@PathVariable Long id, @RequestBody RendezVous rendezVous) throws NotFoundException {
        rendezVous.setId(id);
        RendezVous updatedRendezVous = rendezVousMetier.updateRendezVous(rendezVous);
        return new ResponseEntity<>(updatedRendezVous, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRendezVous(@PathVariable Long id) throws NotFoundException {
        rendezVousMetier.deleteRendezVous(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
