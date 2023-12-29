package com.clinic.metier;

import com.clinic.entity.Patient;
import com.clinic.entity.Enum.GenderType;

import java.util.Date;
import java.util.List;

public interface PatientMetier {

    Patient savePatient(Patient patient);

    Patient getPatientById(Long id);

    List<Patient> getAllPatients();

    Patient updatePatient(Patient patient);

    void deletePatient(Long id);
    
    public List<Patient> searchPatients(String nom , String prenom, String tel ,GenderType sexe ) ;
}
