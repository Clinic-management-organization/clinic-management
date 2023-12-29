package com.clinic.metier;

import com.clinic.entity.Consultation;
import com.clinic.entity.RendezVous;

import java.util.List;

public interface RendezVousMetier {

    /**
     * Enregistre un nouveau rendez-vous.
     *
     * @param rendezVous Le rendez-vous à enregistrer.
     * @return Le rendez-vous enregistré.
     */
    RendezVous saveRendezVous(RendezVous rendezVous);

    /**
     * Récupère un rendez-vous par son identifiant.
     *
     * @param id L'identifiant du rendez-vous.
     * @return Le rendez-vous correspondant à l'identifiant.
     * @throws RendezVousNotFoundException Si le rendez-vous n'est pas trouvé.
     */
    RendezVous getRendezVousById(Long id) ;

    /**
     * Récupère la liste de tous les rendez-vous.
     *
     * @return La liste de tous les rendez-vous.
     */
    List<RendezVous> getAllRendezVous();
    List<RendezVous> getRendezVousByMedecin(Long medecinId);
    List<RendezVous> getRendezVousByPatient(Long patientId);

    /**
     * Met à jour les informations d'un rendez-vous.
     *
     * @param rendezVous Le rendez-vous avec les nouvelles informations.
     * @return Le rendez-vous mis à jour.
     * @throws RendezVousNotFoundException Si le rendez-vous n'est pas trouvé.
     */
    RendezVous updateRendezVous(RendezVous rendezVous);

    /**
     * Supprime un rendez-vous par son identifiant.
     *
     * @param id L'identifiant du rendez-vous à supprimer.
     * @throws RendezVousNotFoundException Si le rendez-vous n'est pas trouvé.
     */
    void deleteRendezVous(Long id) ;
    
    RendezVous addRendezVousToDossier(Long dossierId, RendezVous rendezVous);
}