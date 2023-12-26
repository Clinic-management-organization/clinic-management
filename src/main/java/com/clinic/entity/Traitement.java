package com.clinic.entity;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "traitements")
public class Traitement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

   
    @Column(name = "medicament")
    private String medicament; 

    @Column(name = "dosage")
    private String dosage; 

    @Column(name = "instructions")
    private String instructions;

    @Column(name = "startDate")
    private LocalDate startDate; 

    @Column(name = "endDate")
    private LocalDate endDate; 
    
    @ManyToOne
    @JoinColumn(name = "consultation_id")
    @JsonBackReference
    private Consultation consultation;

}
