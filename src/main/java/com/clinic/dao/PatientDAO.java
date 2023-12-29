package com.clinic.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clinic.entity.Patient;

public interface PatientDAO extends JpaRepository<Patient, Long>{

	@Query("SELECT p FROM Patient p WHERE p.nom = :nom AND p.prenom = :prenom AND p.tel = :tel AND p.dateNaissance = :dateNaissance")
	List<Patient> findByNomAndPrenomAndTelAndDateNaissance(
	    @Param("nom") String nom,
	    @Param("prenom") String prenom,
	    @Param("tel") String tel,
	    @Param("dateNaissance") Date dateNaissance);

}
