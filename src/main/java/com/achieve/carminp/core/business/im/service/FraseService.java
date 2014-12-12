package com.achieve.carminp.core.business.im.service;

import java.util.List;

import javax.ejb.Stateless;

import com.achieve.carminp.core.business.in.service.IFraseService;
import com.achieve.carminp.core.model.ab.dao.GenericDAO;
import com.achieve.carminp.core.model.im.entidade.FraseEntidade;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

/**
 * Classe que implementa as regras de servico definida nas interfaces 
 * para a implementacao dos requisitos necessitados para a transacao.
 * 
 * @author guilherme.magalhaes
 * @version 2.0
 * @since 10/2014
 * @see GenericDAO, {@link FraseEntidade}, {@link IFraseService}
 */
@Stateless
public class FraseService extends GenericDAO<FraseEntidade> implements 
		IFraseService {

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public List<FraseEntidade> getPhrasesWithClauses(int offset, int limit) {
		uaiCriteria = UaiCriteriaFactory.createQueryCriteria(em, FraseEntidade.class);
		uaiCriteria.innerJoinFetch("autor");
		uaiCriteria.setFirstResult(offset);
		uaiCriteria.setMaxResults(limit);
		results = uaiCriteria.getResultList();
		
		return results;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public List<FraseEntidade> getPhrasesByAuthorId(Long id) {
		uaiCriteria = UaiCriteriaFactory.createQueryCriteria(em, FraseEntidade.class);
		uaiCriteria.innerJoinFetch("autor");
		uaiCriteria.andEquals("autor.id", id);
		results = uaiCriteria.getResultList();
		
		return results;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public List<FraseEntidade> getAllPhrases() {
		uaiCriteria = UaiCriteriaFactory.createQueryCriteria(em, FraseEntidade.class);
		uaiCriteria.innerJoinFetch("autor").orderByAsc("id");
		results = uaiCriteria.getResultList();
		
		return results;
	}

}