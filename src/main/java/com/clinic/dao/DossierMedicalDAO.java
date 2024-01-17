package com.clinic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.entity.DossierMedical;

/**
 * Interface de l'accès aux données (DAO) pour l'entité DossierMedical.
 */
public interface DossierMedicalDAO extends JpaRepository<DossierMedical, Long> {

    /**
     * Récupère la liste des dossiers médicaux associés à un patient spécifique.
     *
     * @param patientId L'identifiant du patient.
     * @return La liste des dossiers médicaux associés au patient.
     */
    List<DossierMedical> findDossierMedicalsByRdvs_Patient_Id(Long patientId);
}
