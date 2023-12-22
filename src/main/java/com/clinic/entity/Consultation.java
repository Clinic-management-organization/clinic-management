package com.clinic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float prix;
    
    @Column(columnDefinition = "TEXT") //Cette colonne permettra au médecin de saisir des remarques ou une synthèse liée à la consultation
    private String synthese ; 

    @ManyToOne
    @JoinColumn(name = "dossier_id")
    private DossierMedical dossierMedical;



}
