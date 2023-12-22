package com.clinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.entity.Medecin;


public interface MedecinDAO extends JpaRepository<Medecin, Long>{

}
