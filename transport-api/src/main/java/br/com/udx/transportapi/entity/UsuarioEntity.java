package br.com.udx.transportapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO", schema = "transport")
public class UsuarioEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_USUARIO")
	private Long id;
	
	@Column(name ="EMAIL", nullable = false, length = 255)
	private String email;
	
	@Column(name ="SENHA", nullable = false, length = 255)
	private String senha;
	
	@Column(name ="PERFIL", nullable = false, length = 1)
	private int perfil;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public int getPerfil() {
		return perfil;
	}
	
	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}
}
