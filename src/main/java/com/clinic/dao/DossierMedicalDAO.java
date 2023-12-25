package com.clinic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clinic.entity.DossierMedical;


public interface DossierMedicalDAO extends JpaRepository<DossierMedical, Long>{
	 @Query("SELECT dm FROM DossierMedical dm " +
	            "JOIN dm.rdvs rdv " +
	            "JOIN rdv.patient p " +
	            "WHERE dm.id = rdv.dossierMedical.id AND p.id = :patientId")
	    List<DossierMedical> findDossierMedicalsByPatientId(@Param("patientId") Long patientId);
}
