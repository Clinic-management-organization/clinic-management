package com.clinic.metier.metierImpl;

import com.clinic.dao.TraitementDAO;
import com.clinic.entity.Traitement;
import com.clinic.metier.TraitementMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TraitementMetierImpl implements TraitementMetier {

    private final TraitementDAO traitementDAO;

    @Autowired
    public TraitementMetierImpl(TraitementDAO traitementDAO) {
        this.traitementDAO = traitementDAO;
    }

    @Override
    public Traitement saveTraitement(Traitement traitement) {
        return traitementDAO.save(traitement);
    }

    @Override
    public Traitement getTraitementById(Long id) {
        Optional<Traitement> optionalTraitement = traitementDAO.findById(id);
        return optionalTraitement.orElse(null);
    }

    @Override
    public List<Traitement> getAllTraitements() {
        return traitementDAO.findAll();
    }

    @Override
    public Traitement updateTraitement(Traitement traitement) {
        Long traitementId = traitement.getId();
        if (traitementId != null && traitementDAO.existsById(traitementId)) {
            return traitementDAO.save(traitement);
        }
        return null;
    }

    @Override
    public void deleteTraitement(Long id) {
        if (traitementDAO.existsById(id)) {
            traitementDAO.deleteById(id);
        }
    }
}
