package com.achieve.carminp.core.business.im.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

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
		uaiCriteria.andStringLike(true, "nomeUsuario", "%" + name + "%");
		uaiCriteria.innerJoinFetch("favoritos");
		uaiCriteria.orderByAsc("nomeUsuario");
		uaiCriteria.setDistinctTrue();
		results = uaiCriteria.getResultList();
		
		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UsuarioEntidade getUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioEntidade> getOnlyUser() {
		final Query query = em.createNamedQuery("UsuarioEntidade.getOnlyUser");
		
		return (List<UsuarioEntidade>) query.getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UsuarioEntidade> getUserAndFavoritePhrases() {
		uaiCriteria = UaiCriteriaFactory.createQueryCriteria(em, UsuarioEntidade.class);
		uaiCriteria.innerJoinFetch("favoritos");
		uaiCriteria.orderByAsc("nomeUsuario");
		uaiCriteria.setDistinctTrue();
		results = uaiCriteria.getResultList();
		
		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UsuarioEntidade> getUserAndFavoritePhrasesWithClause(int offset, int limit) {
		uaiCriteria = UaiCriteriaFactory.createQueryCriteria(em, UsuarioEntidade.class);
		uaiCriteria.innerJoinFetch("favoritos");
		uaiCriteria.setFirstResult(offset);
		uaiCriteria.setMaxResults(limit);
		uaiCriteria.orderByAsc("nomeUsuario");
		results = uaiCriteria.getResultList();
		
		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UsuarioEntidade> getOnlyUserByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}