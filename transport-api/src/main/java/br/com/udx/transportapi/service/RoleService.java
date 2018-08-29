package br.com.udx.transportapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udx.transportapi.entity.Role;
import br.com.udx.transportapi.entity.RoleName;
import br.com.udx.transportapi.repository.RoleRepository;


@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public Role findByName(RoleName roleName) {
		return this.roleRepository.findByName(roleName);
	}
}
