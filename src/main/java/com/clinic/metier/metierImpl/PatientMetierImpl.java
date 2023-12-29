package com.clinic.metier.metierImpl;

import com.clinic.dao.PatientDAO;
import com.clinic.entity.Patient;
import com.clinic.entity.Enum.GenderType;
import com.clinic.exception.NotFoundException;
import com.clinic.metier.PatientMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientMetierImpl implements PatientMetier {

    private final PatientDAO patientDAO;

    @Autowired
    public PatientMetierImpl(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientDAO.save(patient);
    }

    @Override
    public Patient getPatientById(Long id) throws NotFoundException {
        Optional<Patient> optionalPatient = patientDAO.findById(id);
        return optionalPatient.orElseThrow(() -> new NotFoundException("Patient not found with id: " + id));
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientDAO.findAll();
    }

    @Override
    public Patient updatePatient(Patient patient) throws NotFoundException {
        Long patientId = patient.getId();
        if (patientId == null || !patientDAO.existsById(patientId)) {
            throw new NotFoundException("Patient not found with id: " + patientId);
        }
        return patientDAO.save(patient);
    }

    @Override
    public void deletePatient(Long id) throws NotFoundException {
        if (!patientDAO.existsById(id)) {
            throw new NotFoundException("Patient not found with id: " + id);
        }
        patientDAO.deleteById(id);
    }

	@Override
	public List<Patient> searchPatients(String nom, String prenom, String tel, GenderType sexe) {
	    return patientDAO.findByNomAndPrenomAndTelAndSexe(nom, prenom, tel, sexe);
	}

}
