package br.com.udx.transportapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udx.transportapi.entity.User;
import br.com.udx.transportapi.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public  Optional<User> findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

    public Optional<User> findByUsernameOrEmail(String username, String email) {
    	return this.userRepository.findByUsernameOrEmail(username, email);
    }

    public List<User> findByIdIn(List<Long> userIds) {
    	return this.userRepository.findByIdIn(userIds);
    }

    public Optional<User> findByUsername(String username) {
    	return this.userRepository.findByUsername(username);
    }

    public Boolean existsByUsername(String username) {
    	return this.userRepository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
    	return this.userRepository.existsByEmail(email);
    }
    
    public User save(User user) {
    	return this.userRepository.save(user);
    }
}
