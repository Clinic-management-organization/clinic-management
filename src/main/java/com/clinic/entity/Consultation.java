package com.clinic.entity;

import com.clinic.entity.Embedded.Horraire;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(name = "date_consultation")
    private Horraire dateConsultation; // date de la consultation par jour et heure

    private float prix;
    
    @Column(columnDefinition = "TEXT") //Cette colonne permettra au médecin de saisir des remarques ou une synthèse liée à la consultation
    private String synthese ; 

    /*@ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;*/

}
