package com.clinic.DTO;

import java.util.List;

import lombok.Data;

@Data
public class ConsultationDTO {
    private Long id;
    private float prix;
    private String synthese;
    private List<TraitementDTO> traitements;
    private List<DiagnosticDTO> diagnostics;
    private DossierMedicalDTO dossierMedical ;
}

