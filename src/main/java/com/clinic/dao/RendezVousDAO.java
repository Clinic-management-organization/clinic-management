package com.clinic.dao;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clinic.entity.Patient;
import com.clinic.entity.RendezVous;
import com.clinic.entity.Enum.EtatRDV;
import com.clinic.entity.Enum.GenderType;

/**
 * Interface de l'accès aux données (DAO) pour l'entité RendezVous.
 */
public interface RendezVousDAO extends JpaRepository<RendezVous, Long> {

    /**
     * Recherche les rendez-vous pour un médecin spécifique.
     *
     * @param id L'identifiant du médecin.
     * @return La liste des rendez-vous associés au médecin.
     */
    List<RendezVous> findByMedecinId(Long id);

    /**
     * Recherche les rendez-vous pour un patient spécifique.
     *
     * @param id L'identifiant du patient.
     * @return La liste des rendez-vous associés au patient.
     */
    List<RendezVous> findByPatientId(Long id);

    /**
     * Recherche les rendez-vous en fonction de l'état et de la date.
     *
     * @param etatRendezVous L'état du rendez-vous (peut être nul).
     * @param dateRendezVous La date du rendez-vous (peut être nul).
     * @return La liste des rendez-vous correspondant aux critères de recherche.
     */
    @Query("SELECT r FROM RendezVous r WHERE " +
           "(:etatRendezVous IS NULL OR r.etatRendezVous = :etatRendezVous) AND " +
           "(:dateRendezVous IS NULL OR CAST(r.dateRendezVous AS DATE) = CAST(:dateRendezVous AS DATE))")
    List<RendezVous> findByEtatRendezVousAndDateRendezVous(
        @Param("etatRendezVous") EtatRDV etatRendezVous,
        @Param("dateRendezVous") Date dateRendezVous
    );

    /**
     * Compte le nombre de rendez-vous par mois.
     *
     * @return La liste des compteurs de rendez-vous par mois et année.
     */
    @Query(value = "SELECT MONTH(r.date_Rendez_Vous) as `month`, YEAR(r.date_Rendez_Vous) as `year`, SUM(1) as count FROM Rendez_Vous r GROUP BY `year`, `month`", nativeQuery = true)
    List<Object[]> countRendezVousByMonth();

}
