package com.clinic.metier.metierImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dao.DossierMedicalDAO;
import com.clinic.dao.RendezVousDAO;
import com.clinic.entity.Consultation;
import com.clinic.entity.DossierMedical;
import com.clinic.entity.RendezVous;
import com.clinic.exception.NotFoundException;
import com.clinic.metier.RendezVousMetier;

@Service
public class RendezVousMetierImpl implements RendezVousMetier {
	
	 private final RendezVousDAO rendezVousDAO;
	 private final DossierMedicalDAO dossierMedicalDAO;

	    @Autowired
	    public RendezVousMetierImpl(RendezVousDAO rendezVousDAO,DossierMedicalDAO dossierMedicalDAO) {
	        this.rendezVousDAO = rendezVousDAO;
	        this.dossierMedicalDAO = dossierMedicalDAO;
	    }

	    @Override
	    public RendezVous saveRendezVous(RendezVous rendezVous) {
	        return rendezVousDAO.save(rendezVous);
	    }

	    @Override
	    public RendezVous getRendezVousById(Long id) throws NotFoundException {
	        Optional<RendezVous> optionalRendezVous = rendezVousDAO.findById(id);
	        return optionalRendezVous.orElseThrow(() -> new NotFoundException("RendezVous not found with id: " + id));
	    }

	    @Override
	    public List<RendezVous> getAllRendezVous() {
	        return rendezVousDAO.findAll();
	    }

	    @Override
	    public RendezVous updateRendezVous(RendezVous rendezVous) throws NotFoundException {
	        Long rendezVousId = rendezVous.getId();
	        if (rendezVousId == null || !rendezVousDAO.existsById(rendezVousId)) {
	            throw new NotFoundException("RendezVous not found with id: " + rendezVousId);
	        }
	        return rendezVousDAO.save(rendezVous);
	    }

	    @Override
	    public List<RendezVous> getRendezVousByMedecin(Long medecinId) {
	        return rendezVousDAO.findByMedecinId(medecinId);
	    }
	    
	    @Override
	    public List<RendezVous> getRendezVousByPatient(Long patientId) {
	        return rendezVousDAO.findByPatientId(patientId);
	    }
	    @Override
	    public void deleteRendezVous(Long id) throws NotFoundException {
	        if (!rendezVousDAO.existsById(id)) {
	            throw new NotFoundException("RendezVous not found with id: " + id);
	        }
	        rendezVousDAO.deleteById(id);
	    }
	    
	    @Override
	    public RendezVous addRendezVousToDossier(Long dossierId, RendezVous rendezVous) {
	        Optional<DossierMedical> optionalDossier = dossierMedicalDAO.findById(dossierId);
	        DossierMedical dossierMedical = optionalDossier.orElseThrow(() -> new NotFoundException("Dossier not found with id: " + dossierId));

	        rendezVous.setDossierMedical(dossierMedical);
	        //dossierMedical.getConsultations().add(consultation);

	        rendezVousDAO.save(rendezVous);
	        
	        return rendezVous;
	    }

}
