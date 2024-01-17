package com.clinic.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clinic.entity.Patient;
import com.clinic.entity.Utilisateur;
import com.clinic.entity.Enum.GenderType;

/**
 * Interface de l'accès aux données (DAO) pour l'entité Patient.
 */
public interface PatientDAO extends JpaRepository<Patient, Long> {

    /**
     * Recherche les patients en fonction des paramètres fournis.
     */
    @Query("SELECT p FROM Patient p WHERE " +
           "(:nom IS NULL OR LOWER(p.nom) LIKE LOWER(CONCAT('%', :nom, '%'))) AND " +
           "(:prenom IS NULL OR LOWER(p.prenom) LIKE LOWER(CONCAT('%', :prenom, '%'))) AND " +
           "(:tel IS NULL OR LOWER(p.tel) LIKE LOWER(CONCAT('%', :tel, '%'))) AND " +
           "(:sexe IS NULL OR p.sexe = :sexe)")
    List<Patient> findByNomAndPrenomAndTelAndSexe(
        @Param("nom") String nom,
        @Param("prenom") String prenom,
        @Param("tel") String tel,
        @Param("sexe") GenderType sexe
    );
}
