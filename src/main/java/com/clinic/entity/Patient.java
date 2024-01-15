package com.clinic.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Patient extends Utilisateur {

  

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String situationFamilliale;
    private String assuranceMedicale;
    
    
    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<RendezVous> RDVs;
    @JsonBackReference
    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private ApplicationUser user;	
}

