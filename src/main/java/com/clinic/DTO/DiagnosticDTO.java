package com.clinic.DTO;

import lombok.Data;

@Data
public class DiagnosticDTO {
    private Long id;
    private String description;
    private String category;
    private String maladie;
    private boolean diagnosticConfirme;
}
