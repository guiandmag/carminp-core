package com.achieve.carminp.core.business.im.service;

import java.util.List;

import javax.ejb.Stateless;

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
		uaiCriteria.innerJoinFetch("frases").andEquals("id", id);
		result = uaiCriteria.getSingleResult();
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AutorEntidade> getOnlyAuthor() {
		results = em.createNamedQuery("AutorEntidade.getOnlyAuthor").getResultList();
		
		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AutorEntidade> getOnlyAuthorByName(String name) {
		results = em.createNamedQuery("AutorEntidade.getOnlyAuthorByName").setParameter("nome", name).getResultList();
		
		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AutorEntidade> getAuthorByNameWithPhrases(String name) {
		uaiCriteria = UaiCriteriaFactory.createQueryCriteria(em, AutorEntidade.class);
		uaiCriteria.innerJoinFetch("frases").andStringLike(true, "nome", "%" + name + "%").orderByAsc("nome").setDistinctTrue();
		results = uaiCriteria.getResultList();
		
		return results;
	}
}