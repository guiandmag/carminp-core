package com.achieve.carminp.core.business.im.service;

import java.util.List;

import javax.ejb.Stateless;

import com.achieve.carminp.core.business.in.service.IUsuarioService;
import com.achieve.carminp.core.model.ab.dao.GenericDAO;
import com.achieve.carminp.core.model.im.entidade.UsuarioEntidade;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

/**
 * Classe que implementa as regras de servico definida nas interfaces 
 * para a implementacao dos requisitos necessitados para a transacao.
 * 
 * @author guilherme.magalhaes
 * @version 2.0
 * @see GenericDAO, {@link UsuarioEntidade}, {@link IUsuarioService}
 */
@Stateless
public class UsuarioService extends GenericDAO<UsuarioEntidade> implements 
		IUsuarioService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UsuarioEntidade> getUsersByName(String name) {
		uaiCriteria = UaiCriteriaFactory.createQueryCriteria(em, UsuarioEntidade.class);
		uaiCriteria.innerJoinFetch("favoritos").andStringLike(true, "nomeUsuario", "%" + name + "%")
															.orderByAsc("nomeUsuario").setDistinctTrue();
		results = uaiCriteria.getResultList();
		
		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UsuarioEntidade getUserById(Long id) {
		uaiCriteria = UaiCriteriaFactory.createQueryCriteria(em, UsuarioEntidade.class);
		uaiCriteria.innerJoinFetch("favoritos").andEquals("id", id);
		result = uaiCriteria.getSingleResult();
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioEntidade> getOnlyUser() {
		results = em.createNamedQuery("UsuarioEntidade.getOnlyUser").getResultList();
		
		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UsuarioEntidade> getUserAndFavoritePhrases() {
		uaiCriteria = UaiCriteriaFactory.createQueryCriteria(em, UsuarioEntidade.class);
		uaiCriteria.innerJoinFetch("favoritos").orderByAsc("nomeUsuario").setDistinctTrue();
		results = uaiCriteria.getResultList();
		
		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UsuarioEntidade> getUserAndFavoritePhrasesWithClause(int offset, int limit) {
		uaiCriteria = UaiCriteriaFactory.createQueryCriteria(em, UsuarioEntidade.class);
		uaiCriteria.innerJoinFetch("favoritos").setFirstResult(offset).setMaxResults(limit).orderByAsc("nomeUsuario");
		results = uaiCriteria.getResultList();
		
		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioEntidade> getOnlyUserByName(String name) {
		results = em.createNamedQuery("UsuarioEntidade.getOnlyUserByName").setParameter("nome", name).getResultList();
		
		return results;
	}

}