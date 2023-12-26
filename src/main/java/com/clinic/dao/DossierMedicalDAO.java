package com.clinic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clinic.entity.DossierMedical;


public interface DossierMedicalDAO extends JpaRepository<DossierMedical, Long>{
	
	 /*@Query("SELECT dm FROM DossierMedical dm JOIN dm.rdvs rdv WHERE rdv.patient.id = :patientId")
	 List<DossierMedical> findDossierMedicalsByPatientId(@Param("patientId") Long patientId);*/
	List<DossierMedical> findDossierMedicalsByRdvs_Patient_Id(Long patientId);
}
