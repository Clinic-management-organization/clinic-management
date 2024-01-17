package com.clinic.metier;

import com.clinic.entity.Diagnostic;

import java.util.List;

public interface DiagnosticMetier {
    // Opérations de base sur les diagnostics

    Diagnostic saveDiagnostic(Diagnostic diagnostic);

    Diagnostic getDiagnosticById(Long id);

    List<Diagnostic> getAllDiagnostics();

    Diagnostic updateDiagnostic(Diagnostic diagnostic);

    void deleteDiagnostic(Long id);
}
