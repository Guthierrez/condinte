package br.com.condinte.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import br.com.condinte.models.Usuario;
import br.com.condinte.util.FacesUtil;

@ManagedBean
@SessionScoped
public class LoginController {
	private Usuario usuario;
	private Subject usuarioCorrente;
	
	public LoginController(){
		this.usuario = new Usuario();
		this.usuarioCorrente = SecurityUtils.getSubject();
	}
	
	public String autenticar(){
		if(!usuarioCorrente.isAuthenticated()){
			try{
				UsernamePasswordToken token = new UsernamePasswordToken(usuario.getLogin(), usuario.getSenha());
				token.setRememberMe(false);
				usuarioCorrente.login(token);
				return "home?faces-redirect=true";
			}catch(UnknownAccountException | IncorrectCredentialsException incorret){
				FacesUtil.addErrorMessage("Erro! Login ou senha incorretos.");
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
