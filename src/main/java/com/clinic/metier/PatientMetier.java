package com.clinic.metier;

import com.clinic.entity.Patient;

import java.util.List;

public interface PatientMetier {

    Patient savePatient(Patient patient);

    Patient getPatientById(Long id);

    List<Patient> getAllPatients();

    Patient updatePatient(Patient patient);

    void deletePatient(Long id);
}
