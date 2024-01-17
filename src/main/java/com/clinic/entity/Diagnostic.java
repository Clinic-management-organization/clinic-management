package com.clinic.entity;

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
@Table(name = "diagnostics")
public class Diagnostic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    
    @Column(name = "description")
    private String description;

    @Column(name = "category",nullable = false)
    private String category; 

    @Column(name = "maladie",nullable = false)
    private String maladie; 
    
    @Column(name = "diagnosticConfirmé")
    private boolean diagnosticConfirmé;


    @ManyToOne
    @JoinColumn(name = "consultation_id")
    @JsonBackReference
    private Consultation consultation;
}
