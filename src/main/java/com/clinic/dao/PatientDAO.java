package com.clinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.entity.Patient;

public interface PatientDAO extends JpaRepository< Patient, Long>{

}
