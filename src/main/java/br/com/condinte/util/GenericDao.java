package br.com.condinte.util;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

@SuppressWarnings("unchecked")
public abstract class GenericDao<T, PK> {
	
	private final EntityManager entityManager;
	private final EntityManagerFactory entityManagerFactory;

	private Class<?> clazz;
	
	public GenericDao(){
		this(DaoFactory.entiManagerFactoryInstance());
	}
	
	public GenericDao(EntityManagerFactory entityManagerFactory){
		
		this.entityManagerFactory = entityManagerFactory;
		this.entityManager = entityManagerFactory.createEntityManager();
		this.clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		
	}
	
	public Object executeQuery(String query, Object... params) {
		Query createdQuery = this.entityManager.createQuery(query);

		for (int i = 0; i < params.length; i++) {
			createdQuery.setParameter(i, params[i]);
		}

		return createdQuery.getResultList();
	}

	public List<T> getLista() {
		return this.entityManager.createQuery(("FROM " + this.clazz.getName()))
				.getResultList();
	}

	public T findById(PK pk) {
		return (T) this.entityManager.find(this.clazz, pk);
	}

	public boolean findBy(T t) {
		return  this.entityManager.contains(t);
	}
	
	public void insert(T entity) {
		try {
			this.beginTransaction();
			this.entityManager.persist(entity);
			this.commit();
		} catch (Exception e) {
			this.rollBack();
			e.printStackTrace();
		}
	}

	public void update(T entity) {
		try {
			this.beginTransaction();
			this.entityManager.merge(entity);
			this.commit();
		} catch (Exception e) {
			this.rollBack();
			e.printStackTrace();
		}
	}

	public void delete(T entity, PK codigo){
		try {
			this.beginTransaction();
		
			this.entityManager.remove(this.findById(codigo));

			this.commit();
		} catch (Exception e) {
			this.rollBack();
			e.printStackTrace();
		}
	}

	
	public void beginTransaction() {
		this.entityManager.getTransaction().begin();
	}
	
	public void commit(){
		this.entityManager.getTransaction().commit();
	}

	public void close(){
		this.entityManager.close();
		this.entityManagerFactory.close();
	}
	
	public void rollBack(){
		this.entityManager.getTransaction().rollback();
	}
	
	public EntityManager getEntityManager(){
		return this.entityManager;
	}
}
