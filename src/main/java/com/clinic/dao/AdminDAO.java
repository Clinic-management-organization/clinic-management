package com.clinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.entity.Admin;


public interface AdminDAO extends JpaRepository<Admin, Long>{

	Admin findByLogin(String username);

}
