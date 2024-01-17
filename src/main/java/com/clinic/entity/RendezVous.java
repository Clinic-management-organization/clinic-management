package com.clinic.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.FetchType;
import com.clinic.entity.Enum.EtatRDV;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class RendezVous implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "etatRendezVous",nullable = false)
    @Enumerated(EnumType.STRING)
    private EtatRDV etatRendezVous;

    @Column(name = "dateRendezVous",nullable = false)
    private Date dateRendezVous; // date de la rendez vous par jour et heure

    
    @ManyToOne
    @JoinColumn(name = "patient_id") 
    private Patient patient;

    @Column(columnDefinition = "TEXT")
    private String remarques;

    private String motif; // Motif du rendez-vous 
    
    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;
    
    @ManyToOne
    @JoinColumn(name = "dossier_id") 
    @JsonIgnore
    private DossierMedical dossierMedical;
}
