package com.achieve.carminp.core.model.ab.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.achieve.carminp.core.model.in.dao.IGenericDAO;
import com.achieve.carminp.core.model.in.entidade.IEntity;
import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

/**
 * Classe abstrata que implementa <code>IGenericDAO</code> e implementa as
 * regras de acesso ao <b>DB</b>, utilizando a API JPA e Reflections.
 * 
 * @author guilherme.magalhaes
 * @since 09/2014
 * @version 1.0
 * @see IGenericDAO, {@link IEntity}
 * @param <T>
 */
public abstract class GenericDAO<T extends IEntity<?>> implements IGenericDAO<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenericDAO.class);
	
	@PersistenceContext(unitName = "carminpDS")
	protected EntityManager em;
	
	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void save(T entity) {
		em.persist(entity);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void update(T entity) {
		Object id = entity.getId();
		
		if(id != null)
			em.merge(entity);
		else 
			LOGGER.info("Entidade nao encontrada {}", entity.getClass().getName());
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void delete(Object id) throws EntityNotFoundException {
		em.remove(this.getById(id));
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public T getById(Object id) throws EntityNotFoundException {
		T entity = null;
		
		try {
			entity = em.find(this.getClassT(), id);
		} catch (EntityNotFoundException e) {
			LOGGER.error("Entidade nao encontrada no db {}", this.getClassT().getName(), e);
		}
		
		return entity;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		Query q = em.createQuery("from " + getClassT().getName() + " t");
		return q.getResultList();
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public List<T> findAllWithClauses(int offset, int limit) {
		EasyCriteria<T> entity = EasyCriteriaFactory.createQueryCriteria(em, getClassT());
		entity.setFirstResult(offset);
		entity.setMaxResults(limit);
		List<T> results = entity.getResultList();
		
		return results;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByFields(Map<String, Object> fields, Boolean exclusive,
			int maxResults, String orderBy) {
		StringBuilder strbld = new StringBuilder("from "  + getClassT().getName() + " t"); 
		String param = ""; 
		String connector = " where "; 
			
		if (fields != null) { 
			for (String key : fields.keySet()) { 
				param = key.replace(".", ""); 
				if (fields.get(key) instanceof String) {
					String strfld = (String) fields.get(key); 
					if (strfld.equals("is null")) 
						strbld.append(connector + "t." + key + " is null"); 
					else if (strfld.equals("is not null")) 
						strbld.append(connector + "t." + key + " is not null"); 
					else if (strfld.startsWith("like ")) 
						strbld.append(connector + "lower(t." + key + ") like lower(:" + param + ")"); 
					else if (strfld.startsWith("!= ")) 
						strbld.append(connector + "t." + key + " != :" + param); 
					else 
						strbld.append(connector + "t." + key + " = :" + param); 
				} else 
					strbld.append(connector + "t." + key + " = :" + param);
				
				if (exclusive) 
					connector = " and "; 
				else 
					connector = " or "; 
			}
		} 
		
		if (orderBy != null) {
			if (!"".equals(orderBy.trim())) { 
				strbld.append(" order by t." + orderBy); 
			} 
		} 
		
		Query q = em.createQuery(strbld.toString()); 
		
		if (maxResults > 0) 
			q.setMaxResults(maxResults); 
			param = ""; 
			
			for (String key : fields.keySet()) { 
				if (!fields.get(key).equals("is null") && !fields.get(key).equals("is not null")) { 
					param = key.replace(".", ""); 
					
					if (fields.get(key) instanceof String) { 
						String strfld = (String) fields.get(key);
						
						if (strfld.startsWith("like ")) { 
							strfld = strfld.replace("like ", "").replace("'", ""); 
							q.setParameter(param, strfld); 
						} else if (strfld.startsWith("!= ")) { 
							strfld = strfld.replace("!= ", ""); 
							q.setParameter(param, strfld); 
						} else 
							q.setParameter(param, fields.get(key)); 
					} else 
						q.setParameter(param, fields.get(key)); 
				}
			} 
			
		return (List<T>) q.getResultList(); 
	}
	
	/**
	 * Classe que utiliza a API de <code>Reflections</code> para recuperar
	 * em runtime qual a classe sera utilizada para as operacoes de CRUD do
	 * sistema.
	 * 
	 * @return
	 */
	private Class<T> getClassT() {
		Type type = getClass().getGenericSuperclass();
		
		if(type instanceof ParameterizedType) {
			ParameterizedType paramType = (ParameterizedType) type;
			
			@SuppressWarnings("unchecked")
			Class<T> tClass = (Class<T>) paramType.getActualTypeArguments()[0];
			
			return tClass;
		} else {
			return null;
		}
	}
}