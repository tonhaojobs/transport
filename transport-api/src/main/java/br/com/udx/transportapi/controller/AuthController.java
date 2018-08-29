package br.com.udx.transportapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.udx.transportapi.entity.Role;
import br.com.udx.transportapi.entity.RoleName;
import br.com.udx.transportapi.entity.User;
import br.com.udx.transportapi.exception.AppException;
import br.com.udx.transportapi.payload.ApiResponse;
import br.com.udx.transportapi.payload.JwtAuthenticationResponse;
import br.com.udx.transportapi.payload.LoginRequest;
import br.com.udx.transportapi.payload.SignUpRequest;
import br.com.udx.transportapi.security.JwtTokenProvider;
import br.com.udx.transportapi.security.UserPrincipal;
import br.com.udx.transportapi.service.RoleService;
import br.com.udx.transportapi.service.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;

	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
	
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		UserPrincipal currentUser = (UserPrincipal) authentication.getPrincipal();
		
		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, currentUser.getName(), currentUser.getRole().getName()));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest, @RequestBody String roleName) {

		if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			System.out.println("tese");
		}
		if (userService.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
		}

		if (userService.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
		}
		
		Role role = null;
		
		if(RoleName.ROLE_ADMIN.equals(roleName)) {
			role = (Role) roleService.findByName(RoleName.ROLE_ADMIN);
		} else if(RoleName.ROLE_COLABORADOR.equals(roleName)) {
			role = (Role) roleService.findByName(RoleName.ROLE_COLABORADOR);
		} else if(RoleName.ROLE_MOTORISTA.equals(roleName)) {
			role = (Role) roleService.findByName(RoleName.ROLE_MOTORISTA);
		} else {
			new AppException("User Role not set.");
		}
		
		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				signUpRequest.getPassword(), role);

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		User result = userService.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	}
}
