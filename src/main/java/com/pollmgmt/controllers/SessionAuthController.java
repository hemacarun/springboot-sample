package com.pollmgmt.controllers;


import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.pollmgmt.exception.CustomAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.pollmgmt.models.ERole;
import com.pollmgmt.models.Role;
import com.pollmgmt.models.User;
import com.pollmgmt.payload.request.LoginRequest;
import com.pollmgmt.payload.request.SignupRequest;
import com.pollmgmt.payload.response.JwtResponse;
import com.pollmgmt.payload.response.MessageResponse;
import com.pollmgmt.repository.RoleRepository;
import com.pollmgmt.repository.UserRepository;
import com.pollmgmt.security.jwt.JwtUtils;
import com.pollmgmt.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/session/api/auth")
public class SessionAuthController {
	
	
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
	  public Map<?,?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpSession session) {

		  System.out.println("sdasdasd");
		 if(loginRequest.getUsername().isEmpty()){
			 throw new CustomAuthenticationException("Username is empty");
		 }
	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));


	    SecurityContextHolder.getContext().setAuthentication(authentication);
	   // String jwt = jwtUtils.generateJwtToken(authentication);
	    
	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
	    List<String> roles = userDetails.getAuthorities().stream()
	        .map(item -> item.getAuthority())
	        .collect(Collectors.toList());
	    
	    System.out.println( userDetails.getEmail());
	    String sessionId = session.getId();  
	    System.out.println("[session-id]: " + sessionId);

		  Map<String, String> test = new HashMap<>();
		  test.put("ID", String.valueOf(userDetails.getId()));
		  test.put("SessionID", sessionId);

	    return test;
	  }


	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleNoSuchElementFoundException(
			BadCredentialsException exception
	) {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(exception.getMessage());
	}


	@RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
	public String fetchSignoutSite(HttpServletRequest request, HttpServletResponse response){

		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
     System.out.println("sddasdas");
		session = request.getSession(false);
		//System.out.println(session);
		if(session != null) {
			session.invalidate();
		}

//		for(Cookie cookie : request.getCookies()) {
//			cookie.setMaxAge(0);
//		}

		return "xxx";
		//return "redirect:/login?logout";
	}


}