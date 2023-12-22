package com.clinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.entity.DossierMedical;
import com.clinic.entity.Horaire;


public interface HoraireDAO extends JpaRepository<Horaire, Long>{

}
