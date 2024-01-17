package com.clinic.metier;

import com.clinic.entity.Patient;
import com.clinic.entity.Enum.GenderType;

import java.util.Date;
import java.util.List;

public interface PatientMetier {

    // Opérations de base sur les patients
    Patient savePatient(Patient patient);

    Patient getPatientById(Long id);

    List<Patient> getAllPatients();

    Patient updatePatient(Patient patient);

    void deletePatient(Long id);

    // Méthode de recherche de patients
    List<Patient> searchPatients(String nom, String prenom, String tel, GenderType sexe);
}
