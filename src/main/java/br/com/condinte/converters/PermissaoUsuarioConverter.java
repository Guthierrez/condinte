package br.com.condinte.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.condinte.dao.PermissaoUsuarioDao;
import br.com.condinte.models.PermissaoUsuario;
import br.com.condinte.util.DaoFactory;


@FacesConverter(forClass = PermissaoUsuario.class)
public class PermissaoUsuarioConverter implements Converter{
	
	private PermissaoUsuarioDao permissaoUsuarioDao = DaoFactory.permissaoUsuarioDaoInstance();

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		PermissaoUsuario permissao = null;
		if (value != null && value != "") {
			permissao = permissaoUsuarioDao.findById(Integer.valueOf(value));
		}
		return permissao;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			PermissaoUsuario permissaoUsuario = (PermissaoUsuario) value;
			return String.valueOf(permissaoUsuario.getId());
		}
		return null;
	}

}
