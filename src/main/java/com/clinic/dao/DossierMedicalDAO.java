package com.clinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.entity.DossierMedical;


public interface DossierMedicalDAO extends JpaRepository<DossierMedical, Long>{

}
