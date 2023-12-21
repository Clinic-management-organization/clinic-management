package com.clinic.entity;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import com.clinic.entity.Embedded.Horraire;
import com.clinic.entity.Enum.GenderType;
import com.clinic.entity.Enum.ServiceType;
import com.clinic.entity.Enum.SpecialiteType;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medecin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    private String adresse;
    private String tel;
    
    @ElementCollection
    @CollectionTable(name = "medecin_horraire", joinColumns = @JoinColumn(name = "medecin_id"))
    @Column(name = "horraire")
    private List<Horraire> horraires; // Liste des horraires disponible pour un medecin

    @Enumerated(EnumType.STRING)
    private GenderType sexe;
    @Enumerated(EnumType.STRING)
    private ServiceType service;
    @Enumerated(EnumType.STRING)
    private SpecialiteType specialite;


}
