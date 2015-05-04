package br.com.condinte.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.condinte.dao.PerfilUsuarioDao;
import br.com.condinte.dao.PermissaoUsuarioDao;
import br.com.condinte.dao.UsuarioDao;

public final class DaoFactory {

	private static final String PERSISTENCE_UNIT_NAME = "condinte-psql";
	private static EntityManagerFactory entityManagerFactoryInstance;

	private static UsuarioDao usuarioDao;
	private static PerfilUsuarioDao perfilUsuarioDao;
	private static PermissaoUsuarioDao permissaoUsuarioDao;

	private DaoFactory() {

	}

	public static EntityManagerFactory entiManagerFactoryInstance() {

		if (entityManagerFactoryInstance == null)
			entityManagerFactoryInstance = Persistence
					.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

		return entityManagerFactoryInstance;
	}

	public static UsuarioDao usuarioDaoInstance() {
		if (usuarioDao == null)
			usuarioDao = new UsuarioDao();

		return usuarioDao;
	}

	public static PerfilUsuarioDao perfilUsuarioDaoInstance() {
		if (perfilUsuarioDao == null)
			perfilUsuarioDao = new PerfilUsuarioDao();

		return perfilUsuarioDao;
	}

	public static PermissaoUsuarioDao permissaoUsuarioDaoInstance() {
		if (permissaoUsuarioDao == null)
			permissaoUsuarioDao = new PermissaoUsuarioDao();

		return permissaoUsuarioDao;
	}
}
