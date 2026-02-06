package com.vidal.vidal.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.repository.UserRepository;
import com.vidal.vidal.entity.*;
import com.vidal.vidal.repository.*;
import com.vidal.vidal.security.JwtUtil;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")

public class AuthController {
	
	@Autowired
    private UserRepository repo;

    @Autowired
    private JwtUtil jwt;
	
	
	@PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {

        if (repo.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Username already exists");
        }

        repo.save(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User Registered Successfully");
    }
	
	
	  @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody User loginUser) {

	        User user = repo.findByUsername(loginUser.getUsername())
	                .orElse(null);

	        if (user == null) {
	            return ResponseEntity
	                    .status(HttpStatus.UNAUTHORIZED)
	                    .body("User not found");
	        }

	        if (!user.getPassword().equals(loginUser.getPassword())) {
	            return ResponseEntity
	                    .status(HttpStatus.UNAUTHORIZED)
	                    .body("Invalid password");
	        }

	        String token = jwt.generateToken(user.getUsername());

	        Map<String, String> response = new HashMap<>();
	        response.put("token", token);
	        response.put("role", user.getRole());

	        return ResponseEntity.ok(response);
	    }
	
	

}
