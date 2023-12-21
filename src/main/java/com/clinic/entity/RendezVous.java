package com.clinic.entity;

import com.clinic.entity.Embedded.Horraire;
import com.clinic.entity.Enum.EtatRDV;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    private EtatRDV etatRendezVous;

    @Column(nullable = false)
    private Horraire dateRendezVous; // date de la rendez vous par jour et heure

    /*@ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medecin_id", nullable = false)
    private Medecin medecin;*/

    @Column(columnDefinition = "TEXT")
    private String remarques;

    private String motif; // Motif du rendez-vous 

}
