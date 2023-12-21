package com.clinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.entity.Facture;

public interface FactureDAO extends JpaRepository<Facture, Long> {

}
