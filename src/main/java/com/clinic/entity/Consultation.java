package com.clinic.entity;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Consultation implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prix",nullable = false)
    private float prix;
    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_creation",nullable = false)
    private Date dateCreation;

    @Column(columnDefinition = "TEXT") //Cette colonne permettra au médecin de saisir des remarques ou une synthèse liée à la consultation
    private String synthese ; 
    
    @OneToMany(mappedBy = "consultation", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Traitement> traitements = new ArrayList<>();

    @OneToMany(mappedBy = "consultation", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Diagnostic> diagnostics = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "dossier_id")
    @JsonIgnore
    private DossierMedical dossierMedical;
}
