package br.com.udx.transportapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.udx.transportapi.entity.Role;
import br.com.udx.transportapi.entity.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
	Role findByName(RoleName roleName);
}
