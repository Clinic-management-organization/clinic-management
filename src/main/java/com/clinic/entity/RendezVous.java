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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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


    @Column(name = "etatRendezVous")
    @Enumerated(EnumType.STRING)
    private EtatRDV etatRendezVous;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateRendezVous")
    private Date dateRendezVous; // date de la rendez vous par jour et heure

    @Column(columnDefinition = "TEXT")
    private String remarques;
    private String motif; // Motif du rendez-vous 
    
    @ManyToOne
    @JoinColumn(name = "patient_id") 
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;
    
    @ManyToOne
    @JoinColumn(name = "dossier_id") 
    @JsonIgnore
    private DossierMedical dossierMedical;
}
