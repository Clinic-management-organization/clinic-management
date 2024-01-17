package com.clinic.controller;

import com.clinic.entity.RendezVous;
import com.clinic.entity.Enum.EtatRDV;
import com.clinic.exception.NotFoundException;
import com.clinic.metier.RendezVousMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/rendezvous")
public class RendezVousController {

    // Déclaration d'une instance de RendezVousMetier pour gérer les opérations métier
    private final RendezVousMetier rendezVousMetier;

    @Autowired
    public RendezVousController(RendezVousMetier rendezVousMetier) {
        this.rendezVousMetier = rendezVousMetier;
    }

    // Endpoint pour obtenir tous les rendez-vous
    @GetMapping
    public ResponseEntity<List<RendezVous>> getAllRendezVous() {
        List<RendezVous> rendezVousList = rendezVousMetier.getAllRendezVous();
        return new ResponseEntity<>(rendezVousList, HttpStatus.OK);
    }

    // Endpoint pour obtenir un rendez-vous par son identifiant
    @GetMapping("/{id}")
    public ResponseEntity<RendezVous> getRendezVousById(@PathVariable Long id) throws NotFoundException {
        RendezVous rendezVous = rendezVousMetier.getRendezVousById(id);
        return new ResponseEntity<>(rendezVous, HttpStatus.OK);
    }

    // Endpoint pour obtenir tous les rendez-vous d'un medecin

    @GetMapping("/medecin/{medecinId}")
    public List<RendezVous> getRendezVousByMedecin(@PathVariable Long medecinId) {
        return rendezVousMetier.getRendezVousByMedecin(medecinId);
    }

    // Endpoint pour obtenir tous les rendez-vous d'un patient

    @GetMapping("/patient/{patientId}")
    public List<RendezVous> getRendezVousByPatient(@PathVariable Long patientId) {
        return rendezVousMetier.getRendezVousByPatient(patientId);
    }

    // Endpoint pour enregistrer un nouveau rendez-vous
    @PostMapping
    public ResponseEntity<RendezVous> saveRendezVous(@RequestBody RendezVous rendezVous) {
        RendezVous savedRendezVous = rendezVousMetier.saveRendezVous(rendezVous);
        return new ResponseEntity<>(savedRendezVous, HttpStatus.CREATED);
    }

    // Endpoint pour ajouter un rendez-vous à un dossier médical spécifique
    @PostMapping("/add-to-dossier/{dossierId}")
    public ResponseEntity<RendezVous> addRendezVousToDossier(
            @PathVariable Long dossierId,
            @RequestBody RendezVous rendezVous) {
        RendezVous savedRDV = rendezVousMetier.addRendezVousToDossier(dossierId, rendezVous);
        return new ResponseEntity<>(savedRDV, HttpStatus.CREATED);
    }

    // Endpoint pour mettre à jour un rendez-vous existant
    @PutMapping("/update/{id}")
    public ResponseEntity<RendezVous> updateRendezVous(@PathVariable Long id, @RequestBody RendezVous rendezVous) throws NotFoundException {
        rendezVous.setId(id);
        RendezVous updatedRendezVous = rendezVousMetier.updateRendezVous(rendezVous);
        return new ResponseEntity<>(updatedRendezVous, HttpStatus.OK);
    }

    // Endpoint pour mettre à jour l'état d'un rendez-vous
    @PutMapping("/updateEtat/{id}")
    public ResponseEntity<RendezVous> updateEtatRendezVous(@PathVariable Long id, @RequestBody EtatRDV newEtat) throws NotFoundException {
        RendezVous existingRendezVous = rendezVousMetier.getRendezVousById(id);

        existingRendezVous.setEtatRendezVous(newEtat);

        RendezVous updatedRendezVous = rendezVousMetier.updateRendezVous(existingRendezVous);
        return new ResponseEntity<>(updatedRendezVous, HttpStatus.OK);
    }

    // Endpoint pour supprimer un rendez-vous par son identifiant
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRendezVous(@PathVariable Long id) throws NotFoundException {
        rendezVousMetier.deleteRendezVous(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint pour rechercher des rendez-vous en fonction de certains critères
    @GetMapping("/search")
    public List<RendezVous> searchRendezVous(
            @RequestParam(required = false) EtatRDV etatRendezVous,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateRendezVous) {
        // Si aucun paramètre n'est fourni, retourne tous les rendez-vous
        if (etatRendezVous == null && dateRendezVous == null) {
            return rendezVousMetier.getAllRendezVous();
        }

        // Appeler la méthode de service avec les paramètres de recherche
        return rendezVousMetier.searchRendezVous(etatRendezVous, dateRendezVous);
    }

    // Endpoint pour obtenir le nombre de rendez-vous par mois
    @GetMapping("/count-by-month")
    public ResponseEntity<List<Object[]>> countRendezVousByMonth() {
        List<Object[]> monthlyRendezVousCount = rendezVousMetier.countRendezVousByMonth();
        return new ResponseEntity<>(monthlyRendezVousCount, HttpStatus.OK);
    }
}
