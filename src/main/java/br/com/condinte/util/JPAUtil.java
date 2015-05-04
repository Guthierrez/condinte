package br.com.condinte.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf = null;
	private static EntityManager manager;
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory("condinte-psql");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static EntityManager getEntityManager() {
		if(manager == null){
			manager = emf.createEntityManager();
		}
		return manager;
	}
}
