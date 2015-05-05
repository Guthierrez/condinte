package br.com.condinte.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.condinte.dao.PerfilUsuarioDao;
import br.com.condinte.models.PerfilUsuario;
import br.com.condinte.util.DaoFactory;

@FacesConverter(forClass = PerfilUsuario.class)
public class PerfilUsuarioConverter implements Converter {
	
	private PerfilUsuarioDao perfilUsuarioDao = DaoFactory.perfilUsuarioDaoInstance();
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		PerfilUsuario perfil = new PerfilUsuario();
		if (value != null && value != "") {
			perfil = perfilUsuarioDao.findById(Integer.valueOf(value));
		}
		return perfil;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value != null){
			PerfilUsuario perfilUsuario = (PerfilUsuario) value;
			return String.valueOf(perfilUsuario.getId());
		}
		return null;
	}

}
