package com.clinic.metier.metierImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dao.ConsultationDAO;
import com.clinic.dao.DossierMedicalDAO;
import com.clinic.entity.Consultation;
import com.clinic.entity.DossierMedical;
import com.clinic.exception.NotFoundException;
import com.clinic.metier.ConsultationMetier;

@Service
public class ConsultationMetierImpl implements ConsultationMetier {
	 private final ConsultationDAO consultationDAO;
	 private final DossierMedicalDAO dossierMedicalDAO;

	    @Autowired
	    public ConsultationMetierImpl(ConsultationDAO consultationDAO, DossierMedicalDAO dossierMedicalDAO) {
	        this.consultationDAO = consultationDAO;
	        this.dossierMedicalDAO = dossierMedicalDAO;
	    }

	    @Override
	    public Consultation saveConsultation(Consultation consultation) {
	        return consultationDAO.save(consultation);
	    }

	    @Override
	    public Consultation getConsultationById(Long id) throws NotFoundException {
	        Optional<Consultation> optionalconsultation = consultationDAO.findById(id);
	        return optionalconsultation.orElseThrow(() -> new NotFoundException("consultation not found with id: " + id));
	    }

	    @Override
	    public List<Consultation> getAllConsultations() {
	        return consultationDAO.findAll();
	    }

	    @Override
	    public Consultation updateConsultation(Consultation consultation) throws NotFoundException {
	        Long consultationId = consultation.getId();
	        if (consultationId == null || !consultationDAO.existsById(consultationId)) {
	            throw new NotFoundException("consultation not found with id: " + consultationId);
	        }
	        return consultationDAO.save(consultation);
	    }

	    @Override
	    public void deleteConsultation(Long id) throws NotFoundException {
	        if (!consultationDAO.existsById(id)) {
	            throw new NotFoundException("consultation not found with id: " + id);
	        }
	        consultationDAO.deleteById(id);
	    }
	    @Override
	    public Consultation addConsultationToDossier(Long dossierId, Consultation consultation) {
	        Optional<DossierMedical> optionalDossier = dossierMedicalDAO.findById(dossierId);
	        DossierMedical dossierMedical = optionalDossier.orElseThrow(() -> new NotFoundException("Dossier not found with id: " + dossierId));

	        consultation.setDossierMedical(dossierMedical);
	        dossierMedical.getConsultations().add(consultation);

	        dossierMedicalDAO.save(dossierMedical);

	        return consultation;
	    }
	    

}
