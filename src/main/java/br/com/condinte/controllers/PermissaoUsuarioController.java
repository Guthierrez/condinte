package br.com.condinte.controllers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.condinte.dao.PermissaoUsuarioDao;
import br.com.condinte.models.PermissaoUsuario;
import br.com.condinte.util.DaoFactory;

@ManagedBean
@ViewScoped
public class PermissaoUsuarioController {
	
	private PermissaoUsuario permissao;
	private PermissaoUsuarioDao permissaoUsuarioDao;
	
	public PermissaoUsuarioController(){
		permissao = new PermissaoUsuario();
		permissaoUsuarioDao = DaoFactory.permissaoUsuarioDaoInstance();
	}
	
	public List<PermissaoUsuario> getAllPermissoes() {
		return permissaoUsuarioDao.getLista();
	}
	
	public String salvar(){
		permissaoUsuarioDao.update(permissao);
		return "listarPermissoes?faces-redirect=true";
	}
	
	public PermissaoUsuario getPermissao() {
		return permissao;
	}
	
	public void setPermissao(PermissaoUsuario permissao) {
		this.permissao = permissao;
	}

}
