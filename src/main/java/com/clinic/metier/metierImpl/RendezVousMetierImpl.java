package com.clinic.metier.metierImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.clinic.dao.RendezVousDAO;
import com.clinic.entity.RendezVous;
import com.clinic.exception.NotFoundException;
import com.clinic.metier.RendezVousMetier;

public class RendezVousMetierImpl implements RendezVousMetier {
	
	 private final RendezVousDAO rendezVousDAO;

	    @Autowired
	    public RendezVousMetierImpl(RendezVousDAO rendezVousDAO) {
	        this.rendezVousDAO = rendezVousDAO;
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
	    public void deleteRendezVous(Long id) throws NotFoundException {
	        if (!rendezVousDAO.existsById(id)) {
	            throw new NotFoundException("RendezVous not found with id: " + id);
	        }
	        rendezVousDAO.deleteById(id);
	    }

}
