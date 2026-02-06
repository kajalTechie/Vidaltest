package com.vidal.vidal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vidal.vidal.entity.*;
import com.vidal.vidal.entity.*;



public interface EmpDetailsRepository extends JpaRepository<EmpDetails, Long> {
    Optional<EmpDetails> findByEmail(String email);
}
