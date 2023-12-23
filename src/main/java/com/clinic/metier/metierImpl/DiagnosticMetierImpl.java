package com.clinic.metier.metierImpl;

import com.clinic.dao.DiagnosticDAO;
import com.clinic.entity.Diagnostic;
import com.clinic.metier.DiagnosticMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosticMetierImpl implements DiagnosticMetier{

    private final DiagnosticDAO diagnosticDAO;

    @Autowired
    public DiagnosticMetierImpl(DiagnosticDAO diagnosticDAO) {
        this.diagnosticDAO = diagnosticDAO;
    }

    @Override
    public Diagnostic saveDiagnostic(Diagnostic diagnostic) {
        return diagnosticDAO.save(diagnostic);
    }

    @Override
    public Diagnostic getDiagnosticById(Long id) {
        Optional<Diagnostic> optionalDiagnostic = diagnosticDAO.findById(id);
        return optionalDiagnostic.orElse(null);
    }

    @Override
    public List<Diagnostic> getAllDiagnostics() {
        return diagnosticDAO.findAll();
    }

    @Override
    public Diagnostic updateDiagnostic(Diagnostic diagnostic) {
        Long diagnosticId = diagnostic.getId();
        if (diagnosticId != null && diagnosticDAO.existsById(diagnosticId)) {
            return diagnosticDAO.save(diagnostic);
        }
        return null;
    }

    @Override
    public void deleteDiagnostic(Long id) {
        if (diagnosticDAO.existsById(id)) {
            diagnosticDAO.deleteById(id);
        }
    }
}
