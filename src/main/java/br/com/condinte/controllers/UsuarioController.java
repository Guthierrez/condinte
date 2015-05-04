package br.com.condinte.controllers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.condinte.dao.PerfilUsuarioDao;
import br.com.condinte.dao.UsuarioDao;
import br.com.condinte.models.PerfilUsuario;
import br.com.condinte.models.Usuario;
import br.com.condinte.util.DaoFactory;

@ManagedBean
@ViewScoped
public class UsuarioController {
	private Usuario usuario;
	private UsuarioDao usuarioDao;
	private PerfilUsuarioDao perfilUsuarioDao;
	private List<PerfilUsuario> perfisCadastrados;
	private PerfilUsuario perfil;
	
	public UsuarioController(){
		usuario = new Usuario();
		usuarioDao = DaoFactory.usuarioDaoInstance();
		perfilUsuarioDao = DaoFactory.perfilUsuarioDaoInstance();
		perfisCadastrados = perfilUsuarioDao.getLista();
		perfil = new PerfilUsuario();
	}
	
	public List<Usuario> getAllUsuarios(){
		return usuarioDao.getLista();
	}
	
	public void salvarUsuario(){
		usuario.setPerfil(perfil);
		usuarioDao.update(usuario);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<PerfilUsuario> getPerfisCadastrados() {
		return perfisCadastrados;
	}

	public void setPerfisCadastrados(List<PerfilUsuario> perfisCadastrados) {
		this.perfisCadastrados = perfisCadastrados;
	}

	public PerfilUsuario getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilUsuario perfil) {
		this.perfil = perfil;
	}
}
