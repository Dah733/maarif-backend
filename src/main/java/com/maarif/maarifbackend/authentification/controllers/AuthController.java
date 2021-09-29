package com.maarif.maarifbackend.authentification.controllers;

import java.util.*;

import javax.validation.Valid;

import com.maarif.maarifbackend.authentification.entities.ERole;
import com.maarif.maarifbackend.authentification.entities.Role;
import com.maarif.maarifbackend.authentification.entities.User;
import com.maarif.maarifbackend.authentification.payload.request.LoginRequest;
import com.maarif.maarifbackend.authentification.payload.request.SignupRequest;
import com.maarif.maarifbackend.authentification.payload.response.JwtResponse;
import com.maarif.maarifbackend.authentification.payload.response.MessageResponse;
import com.maarif.maarifbackend.authentification.repositories.RoleRepository;
import com.maarif.maarifbackend.authentification.repositories.UserRepository;
import com.maarif.maarifbackend.authentification.security.jwt.JwtUtils;
import com.maarif.maarifbackend.authentification.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;


	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = new ArrayList<>();
		for (GrantedAuthority item : userDetails.getAuthorities()) {
			String authority = item.getAuthority();
			roles.add(authority);
		}

		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role decRole = roleRepository.findByName(ERole.ROLE_STUDENTPARENT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(decRole);
		} else {
			for (String role : strRoles)
				switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);
						break;
					case "teacher":
						Role gestionnaireRole = roleRepository.findByName(ERole.ROLE_TEACHER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(gestionnaireRole);
						break;
					case "student":
						Role userRole = roleRepository.findByName(ERole.ROLE_STUDENT)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
					default:
						Role decRole = roleRepository.findByName(ERole.ROLE_STUDENTPARENT)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(decRole);

				}
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
