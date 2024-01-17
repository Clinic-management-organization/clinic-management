package com.clinic.metier;

import java.math.BigDecimal;
import java.util.List;

import com.clinic.entity.Traitement;

public interface TraitementMetier {

    // Opérations de base sur les traitements
    Traitement saveTraitement(Traitement traitement);

    Traitement getTraitementById(Long id);

    List<Traitement> getAllTraitements();

    Traitement updateTraitement(Traitement traitement);

    void deleteTraitement(Long id);

    // Opérations spécifiques aux dossiers médicaux
    List<Traitement> getTraitementsByDossierMedical(Long dossierMedicalId);

    // Opérations liées aux coûts des traitements
    BigDecimal calculateTotalCost();

    BigDecimal calculateMonthlyCost(int year, int month);

    List<Object[]> getMonthlyCost();

    // Autres opérations métier si nécessaires
}
