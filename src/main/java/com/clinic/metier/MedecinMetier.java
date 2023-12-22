package com.clinic.metier;

import com.clinic.entity.Medecin;

import java.util.List;

public interface MedecinMetier {

    /**
     * Enregistre un nouveau médecin.
     *
     * @param medecin Le médecin à enregistrer.
     * @return Le médecin enregistré.
     */
    Medecin saveMedecin(Medecin medecin);

    /**
     * Récupère un médecin par son identifiant.
     *
     * @param id L'identifiant du médecin.
     * @return Le médecin correspondant à l'identifiant.
     * @throws MedecinNotFoundException Si le médecin n'est pas trouvé.
     */
    Medecin getMedecinById(Long id) ;

    /**
     * Récupère la liste de tous les médecins.
     *
     * @return La liste de tous les médecins.
     */
    List<Medecin> getAllMedecins();

    /**
     * Met à jour les informations d'un médecin.
     *
     * @param medecin Le médecin avec les nouvelles informations.
     * @return Le médecin mis à jour.
     * @throws MedecinNotFoundException Si le médecin n'est pas trouvé.
     */
    Medecin updateMedecin(Medecin medecin);

    /**
     * Supprime un médecin par son identifiant.
     *
     * @param id L'identifiant du médecin à supprimer.
     * @throws MedecinNotFoundException Si le médecin n'est pas trouvé.
     */
    void deleteMedecin(Long id) ;
}
