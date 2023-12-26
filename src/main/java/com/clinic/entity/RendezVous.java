package com.clinic.entity;

import java.util.Date;

import jakarta.persistence.FetchType;
import com.clinic.entity.Enum.EtatRDV;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    private EtatRDV etatRendezVous;

    
    private Date dateRendezVous; // date de la rendez vous par jour et heure

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "patient_id") 
    private Patient patient;
    @Column(columnDefinition = "TEXT")
    private String remarques;

    private String motif; // Motif du rendez-vous 
    
    @ManyToOne
    @JoinColumn(name = "medecin_id")
    @JsonManagedReference
    private Medecin medecin;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dossier_id") 
    private DossierMedical dossierMedical;
}
