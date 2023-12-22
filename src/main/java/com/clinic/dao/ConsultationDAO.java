package com.clinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.entity.Consultation;


public interface ConsultationDAO extends JpaRepository<Consultation, Long>{

}
