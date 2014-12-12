package com.achieve.carminp.core.business.im.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.achieve.carminp.core.business.in.service.IAutorService;
import com.achieve.carminp.core.model.ab.dao.GenericDAO;
import com.achieve.carminp.core.model.im.entidade.AutorEntidade;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

/**
 * Classe que implementa as regras de servico definida nas interfaces 
 * para a implementacao dos requisitos necessitados para a transacao.
 * 
 * @author guilherme.magalhaes
 * @version 2.0
 * @see GenericDAO, {@link AutorEntidade}, {@link IAutorService}
 */
@Stateless
public class AutorService extends GenericDAO<AutorEntidade> implements
		IAutorService {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public AutorEntidade getAuthorById(Long id) {
		uaiCriteria = UaiCriteriaFactory.createQueryCriteria(em, AutorEntidade.class);
		uaiCriteria.andEquals("id", id);
		uaiCriteria.innerJoinFetch("frases");
		result = uaiCriteria.getSingleResult();
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AutorEntidade> getOnlyAuthor() {
		final Query query = em.createNamedQuery("AutorEntidade.getOnlyAuthor");
		
		return (List<AutorEntidade>) query.getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AutorEntidade> getOnlyAuthorByName(String name) {
		final Query query = em.createNamedQuery("AutorEntidade.getOnlyAuthorByName");
		query.setParameter("nome", name);
		
		results = (List<AutorEntidade>)query.getResultList();
		
		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AutorEntidade> getAuthorByName(String name) {
		uaiCriteria = UaiCriteriaFactory.createQueryCriteria(em, AutorEntidade.class);
		uaiCriteria.andStringLike(true, "nome", "%" + name + "%");
		uaiCriteria.innerJoinFetch("frases");
		uaiCriteria.orderByAsc("nome");
		uaiCriteria.setDistinctTrue();
		results = uaiCriteria.getResultList();
		
		return results;
	}
}