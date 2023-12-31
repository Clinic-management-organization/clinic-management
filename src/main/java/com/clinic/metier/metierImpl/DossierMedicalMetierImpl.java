package com.clinic.metier.metierImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dao.DossierMedicalDAO;
import com.clinic.entity.DossierMedical;
import com.clinic.exception.NotFoundException;
import com.clinic.metier.DossierMedicalMetier;

@Service
public class DossierMedicalMetierImpl implements DossierMedicalMetier{
	 private final DossierMedicalDAO dossierMedicalDAO;

	    @Autowired
	    public DossierMedicalMetierImpl(DossierMedicalDAO dossierMedicalDAO) {
	        this.dossierMedicalDAO = dossierMedicalDAO;
	    }
	    @Override
	    public List<DossierMedical> getDossiersMedicauxByPatientId(Long id) {
	        return dossierMedicalDAO.findDossierMedicalsByRdvs_Patient_Id(id);
	    }
	    @Override
	    public DossierMedical saveDossierMedical(DossierMedical dossierMedical) {
	        return dossierMedicalDAO.save(dossierMedical);
	    }

	    @Override
	    public DossierMedical getDossierMedicalById(Long id) throws NotFoundException {
	        Optional<DossierMedical> optionalDossierMedical = dossierMedicalDAO.findById(id);
	        return optionalDossierMedical.orElseThrow(() -> new NotFoundException("DossierMedical not found with id: " + id));
	    }

	    @Override
	    public List<DossierMedical> getAllDossiersMedicals() {
	        return dossierMedicalDAO.findAll();
	    }

	    @Override
	    public DossierMedical updateDossierMedical(DossierMedical dossierMedical) throws NotFoundException {
	        Long dossierMedicalId = dossierMedical.getId();
	        if (dossierMedicalId == null || !dossierMedicalDAO.existsById(dossierMedicalId)) {
	            throw new NotFoundException("DossierMedical not found with id: " + dossierMedicalId);
	        }
	        return dossierMedicalDAO.save(dossierMedical);
	    }

	    @Override
	    public void deleteDossierMedical(Long id) throws NotFoundException {
	        if (!dossierMedicalDAO.existsById(id)) {
	            throw new NotFoundException("DossierMedical not found with id: " + id);
	        }
	        dossierMedicalDAO.deleteById(id);
	    }

}
