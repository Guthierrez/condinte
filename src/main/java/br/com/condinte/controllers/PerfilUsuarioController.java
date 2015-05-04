package br.com.condinte.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

import br.com.condinte.dao.PerfilUsuarioDao;
import br.com.condinte.dao.PermissaoUsuarioDao;
import br.com.condinte.models.PerfilUsuario;
import br.com.condinte.models.PermissaoUsuario;
import br.com.condinte.util.DaoFactory;

@ManagedBean
@ViewScoped
public class PerfilUsuarioController {
	private PerfilUsuario perfil;
	private PerfilUsuarioDao perfilUsuarioDao;
	private PermissaoUsuarioDao permissaoUsuarioDao;
	private List<PermissaoUsuario> permissoesCadastradas;
	private List<PermissaoUsuario> permissoesDestino;
	private DualListModel<PermissaoUsuario> permissoesPickList;
	
	public PerfilUsuarioController(){
		perfil = new PerfilUsuario();
		perfilUsuarioDao = DaoFactory.perfilUsuarioDaoInstance();
		permissaoUsuarioDao = DaoFactory.permissaoUsuarioDaoInstance();
		permissoesCadastradas = permissaoUsuarioDao.getLista();
		permissoesDestino = new ArrayList<PermissaoUsuario>(perfil.getPermissoes());
		permissoesPickList = new DualListModel<PermissaoUsuario>(permissoesCadastradas, permissoesDestino);
	}
	
	public List<PerfilUsuario> getAllPerfis(){
		return perfilUsuarioDao.getLista();
	}

	public String salvarPerfil(){
		perfil.setPermissoes(permissoesPickList.getTarget());
		perfilUsuarioDao.update(perfil);
		perfil = new PerfilUsuario();
		return "/home?faces-redirect=true";
	}
	
	public void teste(String id){
		if (id != null && id != ""){
			List<PermissaoUsuario> permissoes = perfilUsuarioDao.findById(Integer.valueOf(id)).getPermissoes();
			if(!permissoes.isEmpty()){
				permissoes.forEach(permissao -> {
					permissoesPickList.getTarget().add(permissao);
				});
			}
		}
	}
	
	public void onSelect(SelectEvent event) {
		PermissaoUsuario permissao = (PermissaoUsuario) event.getObject();
		if(permissoesPickList.getTarget().contains(permissao)){
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Item já selecionado", event.getObject().toString()));
		}
    }

	public PerfilUsuario getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilUsuario perfil) {
		this.perfil = perfil;
	}

	public DualListModel<PermissaoUsuario> getPermissoesPickList() {
		return permissoesPickList;
	}

	public void setPermissoesPickList(DualListModel<PermissaoUsuario> permissoes) {
		this.permissoesPickList = permissoes;
	}
}
