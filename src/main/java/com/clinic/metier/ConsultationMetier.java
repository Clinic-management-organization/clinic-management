package com.clinic.metier;

import com.clinic.entity.Consultation;

import java.util.List;

public interface ConsultationMetier {

    /**
     * Enregistre une nouvelle consultation.
     *
     * @param consultation La consultation à enregistrer.
     * @return La consultation enregistrée.
     */
    Consultation saveConsultation(Consultation consultation);

    /**
     * Récupère une consultation par son identifiant.
     *
     * @param id L'identifiant de la consultation.
     * @return La consultation correspondant à l'identifiant.
     * @throws ConsultationNotFoundException Si la consultation n'est pas trouvée.
     */
    Consultation getConsultationById(Long id) ;

    /**
     * Récupère la liste de toutes les consultations.
     *
     * @return La liste de toutes les consultations.
     */
    List<Consultation> getAllConsultations();

    /**
     * Met à jour les informations d'une consultation.
     *
     * @param consultation La consultation avec les nouvelles informations.
     * @return La consultation mise à jour.
     * @throws ConsultationNotFoundException Si la consultation n'est pas trouvée.
     */
    Consultation updateConsultation(Consultation consultation);

    /**
     * Supprime une consultation par son identifiant.
     *
     * @param id L'identifiant de la consultation à supprimer.
     * @throws ConsultationNotFoundException Si la consultation n'est pas trouvée.
     */
    void deleteConsultation(Long id) ;

    Consultation addConsultationToDossier(Long dossierId, Consultation consultation);

}