package com.clinic.DTO;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class DossierMedicalDTO {
    private Long id;
    private Date dateCreation;
    private Date dateMiseAJour;
    private String observation;
    private List<ConsultationDTO> consultations;
    private List<RendezVousDTO> rdvs;
}
