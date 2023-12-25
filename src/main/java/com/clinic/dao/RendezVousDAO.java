package com.clinic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.entity.RendezVous;



public interface RendezVousDAO extends JpaRepository<RendezVous, Long>{
	public List<RendezVous> findByMedecinId(Long id);
	public List<RendezVous> findByPatientId(Long id);
}
