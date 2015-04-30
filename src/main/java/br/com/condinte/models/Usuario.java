package br.com.condinte.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	private String login;

	@Column(nullable = false)
	private String senha;

	@Column(unique = true, nullable = false, length = 11)
	private String cpf;

	@Column(nullable = false)
	private String nome;

	@Column(unique = true, nullable = false)
	private String email;

	@ManyToOne
	@JoinColumn(name = "perfil_nome")
	private PerfilUsuario perfil;

	@NotBlank(message = "Digite seu login!")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@NotBlank(message = "Digite sua senha!")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PerfilUsuario getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilUsuario perfil) {
		this.perfil = perfil;
	}
}
