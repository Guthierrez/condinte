package br.com.condinte.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import br.com.condinte.models.Usuario;
import br.com.condinte.util.JPAUtil;

@ManagedBean
@SessionScoped
public class LoginController {
	private Usuario usuario;
	private Subject usuarioCorrente;
	//codigo temporario somente para inicializar o hibernate
	@SuppressWarnings("unused")
	private static EntityManager manager;
	
	public LoginController(){
		this.usuario = new Usuario();
		this.usuarioCorrente = SecurityUtils.getSubject();
		//codigo temporario somente para inicializar o hibernate
		manager = JPAUtil.getEntityManager();
	}
	
	public String autenticar(){
		if(!usuarioCorrente.isAuthenticated()){
			try{
				UsernamePasswordToken token = new UsernamePasswordToken(usuario.getLogin(), usuario.getSenha());
				token.setRememberMe(false);
				usuarioCorrente.login(token);
				return "home?faces-redirect=true";
			}catch(UnknownAccountException usuarioNaoCadastro){
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro! Login ou senha incorretos.", null));
			}
		}
		return null;
	}
	
	public String sair(){
		return "login?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
