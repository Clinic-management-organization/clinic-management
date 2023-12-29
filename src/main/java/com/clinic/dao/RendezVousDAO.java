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



public interface RendezVousDAO extends JpaRepository<RendezVous, Long> {
    @Query("SELECT r FROM RendezVous r WHERE " +
           "(:etatRendezVous IS NULL OR r.etatRendezVous = :etatRendezVous) AND " +
           "(:dateRendezVous IS NULL OR r.dateRendezVous = :dateRendezVous)")
    List<RendezVous> findByEtatRendezVousAndDateRendezVous(
        @Param("etatRendezVous") EtatRDV etatRendezVous,
        @Param("dateRendezVous") String dateRendezVous
    );
}
