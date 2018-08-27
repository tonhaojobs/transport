package br.com.udx.transportapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.udx.transportapi.entity.UsuarioEntity;
import br.com.udx.transportapi.service.UsuarioService;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    AuthenticationManager authenticationManager;
	
	@PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UsuarioEntity usuario) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
}

/*
 * 
 * CREATE TABLE transport.USUARIO(
	ID_USUARIO INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    EMAIL VARCHAR(255) NOT NULL,
    SENHA VARCHAR(255) NOT NULL,
    PERFIL INT NOT NULL
);

CREATE TABLE transport.PERFIL(
	ID_PERFIL INT NOT NULL PRIMARY KEY auto_increment,
    DESCRICAO VARCHAR(30) NOT NULL
);



INSERT INTO TRANSPORT.PERFIL (DESCRICAO) VALUES ('MOTORISTA');
INSERT INTO TRANSPORT.PERFIL (DESCRICAO) VALUES ('COLABORADOR');




INSERT INTO TRANSPORT.USUARIO (EMAIL, SENHA, PERFIL) VALUES ('antoniocarlo@hotmail.com', '12345', 2);

SELECT * FROM TRANSPORT.PERFIL;

SELECT * FROM TRANSPORT.usuario;


select u.email, p.descricao 
from TRANSPORT.usuario u
inner join TRANSPORT.perfil p
on u.perfil = p.id_perfil
where u.email = 'antoniocarlo@hotmail.com'


UPDATE TRANSPORT.USUARIO  
SET SENHA = '$2a$10$S4K9NqYDSQ2amVOK/OKnIemop/OuowyHquhpePmEnZeQ0uFXaouFO' 
WHERE ID_USUARIO = 1;
 * 
 * 
 * 
 * 
 * */
