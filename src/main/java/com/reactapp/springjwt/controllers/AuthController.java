package com.reactapp.springjwt.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactapp.springjwt.models.ERole;
import com.reactapp.springjwt.models.Role;
import com.reactapp.springjwt.models.User;
import com.reactapp.springjwt.payload.request.ChangePasswordRequest;
import com.reactapp.springjwt.payload.request.LoginRequest;
import com.reactapp.springjwt.payload.request.SignupRequest;
import com.reactapp.springjwt.payload.response.JwtResponse;
import com.reactapp.springjwt.payload.response.MessageResponse;
import com.reactapp.springjwt.repository.RoleRepository;
import com.reactapp.springjwt.repository.UserRepository;
import com.reactapp.springjwt.security.jwt.JwtUtils;
import com.reactapp.springjwt.security.services.UserDetailsImpl;

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
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

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
          .body(new MessageResponse("Λάθος: Το όνομα χρήστη υπάρχει!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Λάθος: Το Email χρησιμοποιείται!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(), 
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("Ο χρήστης καταχωρήθηκε σωστά!"));
  }
  
  @PutMapping("/changepassword")
  public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {

	boolean isWithinDb = false;  
	
	Optional<User> user = Optional.empty();
	
    //Check if it is a username or email and try to find if it exists in the database
    if (changePasswordRequest.getUsername()!=null) {
    	isWithinDb = userRepository.existsByUsername(changePasswordRequest.getUsername());
    	
    	user = userRepository.findByUsername(changePasswordRequest.getUsername());
    	
    } else if (!isWithinDb && changePasswordRequest.getEmail()!=null) {
    	isWithinDb = userRepository.existsByEmail(changePasswordRequest.getEmail());
    	
    	user = userRepository.findByEmail(changePasswordRequest.getEmail());
    	
    }
    
    if (isWithinDb) {
    	//The user exists in db 
    	//Check if the password and verifyPassword are the same
    	changeUserPassword(user.get(),changePasswordRequest.getPassword());
    	
    	return ResponseEntity.ok(new MessageResponse("Επιτυχής αλλαγή password!"));
    	
    } else {
    	
    	return ResponseEntity.ok(new MessageResponse("Ανεπιτυχής αλλαγή password!"));
    	
    }
    
    
  }
  
  public void changeUserPassword(User user, String password) {
	    user.setPassword(encoder.encode(password));
	    userRepository.save(user);
	}
  
  
}
