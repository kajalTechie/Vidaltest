package com.vidal.vidal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vidal.vidal.entity.User;

@Repository
public class UserRepository extends JpaRepository<User , Long> {
	
    Optional<User> findByUsername(String username);

}


