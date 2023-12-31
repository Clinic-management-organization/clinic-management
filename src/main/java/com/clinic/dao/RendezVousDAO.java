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



public interface RendezVousDAO extends JpaRepository<RendezVous, Long>{
	public List<RendezVous> findByMedecinId(Long id);
	public List<RendezVous> findByPatientId(Long id);
	
	@Query("SELECT r FROM RendezVous r WHERE " +
		       "(:etatRendezVous IS NULL OR r.etatRendezVous =:etatRendezVous ) AND " +
		       "(:dateRendezVous IS NULL OR CAST(r.dateRendezVous AS DATE) = CAST(:dateRendezVous AS DATE))")
	public List<RendezVous> findByEtatRendezVousAndDateRendezVous(
	    @Param("etatRendezVous") EtatRDV etatRendezVous,
	    @Param("dateRendezVous") Date dateRendezVous
	);
	@Query(value = "SELECT MONTH(r.date_Rendez_Vous) as `month`, YEAR(r.date_Rendez_Vous) as `year`, COUNT(r.id) as count FROM Rendez_Vous r GROUP BY  `year`, `month`", nativeQuery = true)
	List<Object[]> countRendezVousByMonth();



}
