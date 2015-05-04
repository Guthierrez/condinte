package br.com.condinte.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.condinte.dao.UsuarioDao;
import br.com.condinte.models.Usuario;
import br.com.condinte.util.DaoFactory;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	private UsuarioDao usuarioDao = DaoFactory.usuarioDaoInstance();

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario usuario = null;
		if (value != null && value != "") {
			usuario = usuarioDao.findById(Integer.valueOf(value));
		}
		return usuario;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			Usuario usuario = (Usuario) value;
			return String.valueOf(usuario.getId());
		}
		return null;
	}

}
