package com.clinic.metier;

import com.clinic.entity.DossierMedical;

import java.util.List;

public interface DossierMedicalMetier {

    /**
     * Enregistre un nouveau dossier médical.
     *
     * @param dossierMedical Le dossier médical à enregistrer.
     * @return Le dossier médical enregistré.
     */
    DossierMedical saveDossierMedical(DossierMedical dossierMedical);

    /**
     * Récupère un dossier médical par son identifiant.
     *
     * @param id L'identifiant du dossier médical.
     * @return Le dossier médical correspondant à l'identifiant.
     * @throws DossierMedicalNotFoundException Si le dossier médical n'est pas trouvé.
     */
    DossierMedical getDossierMedicalById(Long id) ;

    /**
     * Récupère la liste de tous les dossiers médicaux.
     *
     * @return La liste de tous les dossiers médicaux.
     */
    List<DossierMedical> getAllDossiersMedicals();

    /**
     * Met à jour les informations d'un dossier médical.
     *
     * @param dossierMedical Le dossier médical avec les nouvelles informations.
     * @return Le dossier médical mis à jour.
     * @throws DossierMedicalNotFoundException Si le dossier médical n'est pas trouvé.
     */
    DossierMedical updateDossierMedical(DossierMedical dossierMedical) ;

    /**
     * Supprime un dossier médical par son identifiant.
     *
     * @param id L'identifiant du dossier médical à supprimer.
     * @throws DossierMedicalNotFoundException Si le dossier médical n'est pas trouvé.
     */
    void deleteDossierMedical(Long id) ;
}