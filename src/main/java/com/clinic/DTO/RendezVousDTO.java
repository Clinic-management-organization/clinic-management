package com.clinic.DTO;

import java.util.Date;

import com.clinic.entity.Enum.EtatRDV;

import lombok.Data;

@Data
public class RendezVousDTO {
    private Long id;
    private EtatRDV etatRendezVous;
    private Date dateRendezVous;
    private PatientDTO patient;
    private String remarques;
    private String motif;
    private MedecinDTO medecin;
}

