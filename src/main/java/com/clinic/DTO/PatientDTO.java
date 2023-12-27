package com.clinic.DTO;

import lombok.Data;

@Data
public class PatientDTO extends UtilisateurDTO {
    private String situationFamilliale;
    private String assuranceMedicale;
}
