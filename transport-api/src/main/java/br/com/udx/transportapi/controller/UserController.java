package br.com.udx.transportapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.udx.transportapi.entity.User;
import br.com.udx.transportapi.exception.ResourceNotFoundException;
import br.com.udx.transportapi.payload.UserIdentityAvailability;
import br.com.udx.transportapi.payload.UserProfile;
import br.com.udx.transportapi.payload.UserSummary;
import br.com.udx.transportapi.repository.UserRepository;
import br.com.udx.transportapi.security.CurrentUser;
import br.com.udx.transportapi.security.UserPrincipal;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/me")
	//@PreAuthorize("hasRole('USER')")
	public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(),
				currentUser.getName());
		return userSummary;
	}

	@GetMapping("/checkUsernameAvailability")
	public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
		Boolean isAvailable = !userRepository.existsByUsername(username);
		return new UserIdentityAvailability(isAvailable);
	}

	@GetMapping("/checkEmailAvailability")
	public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
		Boolean isAvailable = !userRepository.existsByEmail(email);
		return new UserIdentityAvailability(isAvailable);
	}

	@GetMapping("/{username}")
	public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

		UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(),
				user.getCreatedAt());

		return userProfile;
	}
}
