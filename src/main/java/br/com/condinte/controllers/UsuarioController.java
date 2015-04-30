package br.com.condinte.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import br.com.condinte.models.Usuario;
import br.com.condinte.util.JPAUtil;

@ManagedBean
@SessionScoped
public class UsuarioController {
	private Usuario usuario;
	private Subject usuarioCorrente;
	//codigo temporario somente para inicializar o hibernate
	private static EntityManager manager;
	
	public UsuarioController(){
		this.usuario = new Usuario();
		this.usuarioCorrente = SecurityUtils.getSubject();
		//codigo temporario somente para inicializar o hibernate
		manager = JPAUtil.getEntityManager();
	}
	
	public String autenticar(){
		UsernamePasswordToken token = new UsernamePasswordToken(usuario.getLogin(), usuario.getSenha());
		usuarioCorrente.login(token);
		return "home?faces-redirect=true";  
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
