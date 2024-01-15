package com.clinic.metier;

import com.clinic.entity.Consultation;
import com.clinic.entity.Patient;
import com.clinic.entity.RendezVous;
import com.clinic.entity.Enum.EtatRDV;
import com.clinic.entity.Enum.GenderType;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

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
    /*List<RendezVous> getRendezVousByMedecin(Long medecinId);
    List<RendezVous> getRendezVousByPatient(Long patientId);*/

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
    
    /**
     * Ajouter un rendez-vous par l'identifiant du dossier medical.
     *
     * @param id L'identifiant du dossier Medical.
     * @throws RendezVousNotFoundException Si le rendez-vous n'est pas trouvé.
     */
    RendezVous addRendezVousToDossier(Long dossierId, RendezVous rendezVous);

    /**
     * Rechercher un rendez-vous par son etat ou sa date.
     *
     * @param Etat et Date rendez vous .
     * @throws RendezVousNotFoundException Si le rendez-vous n'est pas trouvé.
     */

	public List<RendezVous> searchRendezVous(EtatRDV etatRendezVous, Date dateRendezVous);
    List<Object[]> countRendezVousByMonth();
    
    List<RendezVous> getRendezVousByPatient(Long patientID);



}