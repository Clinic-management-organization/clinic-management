package com.clinic.DTO;

import lombok.Data;

@Data
public class TraitementDTO {
    private Long id;
    private String medicament;
    private String dosage;
    private String instructions;
    private String startDate;
    private String endDate;
}
