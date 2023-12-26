package com.clinic.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "dossier_medical")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DossierMedical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date_creation", nullable = false)
    private LocalDateTime dateCreation;
    @Column(name = "date_mise_a_jour")
    private LocalDateTime dateMiseAJour;
    @Column(name = "observation")
    private String observation;

    @OneToMany(mappedBy = "dossierMedical", fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Consultation> consultations;
    
    @OneToMany(mappedBy = "dossierMedical", fetch=FetchType.LAZY)
    @JsonIgnore
    private List<RendezVous> rdvs;
}