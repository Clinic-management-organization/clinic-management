package com.clinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.entity.Diagnostic;

public interface DiagnosticDAO extends JpaRepository<Diagnostic, Long>{

}
