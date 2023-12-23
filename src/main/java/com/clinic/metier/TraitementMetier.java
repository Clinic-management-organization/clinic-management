package com.clinic.metier;

import java.util.List;

import com.clinic.entity.Traitement;

public interface TraitementMetier {
    Traitement saveTraitement(Traitement traitement);

    Traitement getTraitementById(Long id);

    List<Traitement> getAllTraitements();

    Traitement updateTraitement(Traitement traitement);

    void deleteTraitement(Long id);
}
