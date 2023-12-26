package com.clinic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.entity.DossierMedical;


public interface DossierMedicalDAO extends JpaRepository<DossierMedical, Long>{
	List<DossierMedical> findDossierMedicalsByRdvs_Patient_Id(Long patientId);
}
