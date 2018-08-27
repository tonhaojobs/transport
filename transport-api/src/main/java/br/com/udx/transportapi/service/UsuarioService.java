package br.com.udx.transportapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udx.transportapi.entity.UsuarioEntity;
import br.com.udx.transportapi.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UsuarioEntity findUsuarioByEmail(String email) {
		return this.usuarioRepository.findByEmail(email);
	}
}
