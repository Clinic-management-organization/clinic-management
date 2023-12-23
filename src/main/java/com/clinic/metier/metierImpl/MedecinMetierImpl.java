package com.clinic.metier.metierImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dao.MedecinDAO;
import com.clinic.entity.Medecin;
import com.clinic.exception.NotFoundException;
import com.clinic.metier.MedecinMetier;

@Service
public class MedecinMetierImpl implements MedecinMetier {
	 private final MedecinDAO medecinDAO;

	    @Autowired
	    public MedecinMetierImpl(MedecinDAO medecinDAO) {
	        this.medecinDAO = medecinDAO;
	    }

	    @Override
	    public Medecin saveMedecin(Medecin medecin) {
	        return medecinDAO.save(medecin);
	    }

	    @Override
	    public Medecin getMedecinById(Long id) throws NotFoundException {
	        Optional<Medecin> optionalMedecin = medecinDAO.findById(id);
	        return optionalMedecin.orElseThrow(() -> new NotFoundException("Medecin not found with id: " + id));
	    }

	    @Override
	    public List<Medecin> getAllMedecins() {
	        return medecinDAO.findAll();
	    }

	    @Override
	    public Medecin updateMedecin(Medecin Medecin) throws NotFoundException {
	        Long MedecinId = Medecin.getId();
	        if (MedecinId == null || !medecinDAO.existsById(MedecinId)) {
	            throw new NotFoundException("Medecin not found with id: " + MedecinId);
	        }
	        return medecinDAO.save(Medecin);
	    }

	    @Override
	    public void deleteMedecin(Long id) throws NotFoundException {
	        if (!medecinDAO.existsById(id)) {
	            throw new NotFoundException("Medecin not found with id: " + id);
	        }
	        medecinDAO.deleteById(id);
	    }
	

}
