package com.clinic.dao;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.entity.Role;

@Repository
public interface RoleDAO extends JpaRepository<Role, Integer>{
    Optional<Role> findByAuthority(String authority);
}