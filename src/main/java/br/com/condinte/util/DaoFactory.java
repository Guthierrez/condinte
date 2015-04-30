package br.com.condinte.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.condinte.dao.UsuarioDao;

public final class DaoFactory {

	private static final String PERSISTENCE_UNIT_NAME = "ticvip";
	private static EntityManagerFactory entityManagerFactoryInstance;
	
	private static UsuarioDao usuarioDao;
	
	private DaoFactory(){}
	
	public static EntityManagerFactory entiManagerFactoryInstance() {
		
		if(entityManagerFactoryInstance == null)
			entityManagerFactoryInstance = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		
		return entityManagerFactoryInstance;
	}
	
	public static UsuarioDao eventoDaoInstance() {
		if(usuarioDao == null)
			usuarioDao = new UsuarioDao();
		
		return usuarioDao;
	}
	
}
