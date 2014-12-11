package com.achieve.carminp.core.business.im.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.achieve.carminp.core.business.in.service.IFraseService;
import com.achieve.carminp.core.model.ab.dao.GenericDAO;
import com.achieve.carminp.core.model.im.entidade.FraseEntidade;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

/**
 * Classe que implementa as regras de servico definida nas interfaces 
 * para a implementacao dos requisitos necessitados para a transacao.
 * 
 * @author guilherme.magalhaes
 * @version 1.1
 * @since 10/2014
 * @see GenericDAO, {@link FraseEntidade}, {@link IFraseService}
 */
@Stateless
public class FraseService extends GenericDAO<FraseEntidade> implements 
		IFraseService {

	UaiCriteria<FraseEntidade> uaiCriteria;
	
	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public List<FraseEntidade> getPhrasesWithClauses(int offset, int limit) {
		uaiCriteria = UaiCriteriaFactory.createQueryCriteria(em, FraseEntidade.class);
		uaiCriteria.setFirstResult(offset);
		uaiCriteria.setMaxResults(limit);
		final List<FraseEntidade> results = uaiCriteria.getResultList();
		
		return results;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public List<FraseEntidade> getPhrasesByAuthorId(Long id) {
		uaiCriteria = UaiCriteriaFactory.createQueryCriteria(em, FraseEntidade.class);
		uaiCriteria.innerJoin("autor");
		uaiCriteria.andEquals("autor.id", id);
		final List<FraseEntidade> fraseEntidade = uaiCriteria.getResultList();
		
		return fraseEntidade;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public List<FraseEntidade> getPhrasesByAuthorName(String name) {
		uaiCriteria = UaiCriteriaFactory.createQueryCriteria(em, FraseEntidade.class);
		uaiCriteria.innerJoin("autor");
		uaiCriteria.andStringLike(true, "autor.nome", "%" + name + "%");
		final List<FraseEntidade> frasesEntidade = uaiCriteria.getResultList();
		
		return frasesEntidade;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FraseEntidade> getAllPhrases() {
		Query q = em.createQuery("SELECT f.frase_id, f.frase_frase, f.frase_avaliacao FROM tbl_frase f");
		return (List<FraseEntidade>)q.getResultList();
	}

}