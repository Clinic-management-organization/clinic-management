package com.clinic.entity;


import java.util.List;

import com.clinic.entity.Enum.SpecialiteType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medecin extends Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    @Enumerated(EnumType.STRING)
    private SpecialiteType specialite;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private ApplicationUser user;

    @OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL)    
    private List<Horaire> horaires; // Liste des horraires disponible pour un medecin
    
    @JsonIgnore
    @OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL)
    private List<RendezVous> RDVs;
}
