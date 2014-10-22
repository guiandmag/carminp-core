package com.achieve.carminp.core.business.im.service;

import java.util.List;

import javax.ejb.Stateless;

import com.achieve.carminp.core.business.in.service.IFraseService;
import com.achieve.carminp.core.model.ab.dao.GenericDAO;
import com.achieve.carminp.core.model.im.entidade.FraseEntidade;
import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

/**
 * Classe que implementa as regras de servico definida nas interfaces 
 * para a implementacao dos requisitos necessitados para a transacao.
 * 
 * @author guilherme.magalhaes
 * @version 1.0
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
	public List<FraseEntidade> getPhrasesByAuthorId(Long id) {
		EasyCriteria<FraseEntidade> easyCriteria = EasyCriteriaFactory.createQueryCriteria(em, FraseEntidade.class);
		easyCriteria.innerJoin("autor");
		easyCriteria.andEquals("id", id);
		List<FraseEntidade> fraseEntidade = easyCriteria.getResultList();
		
		return fraseEntidade;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public List<FraseEntidade> getPhrasesByAuthorName(String name) {
		EasyCriteria<FraseEntidade> easyCriteria = EasyCriteriaFactory.createQueryCriteria(em, FraseEntidade.class);
		easyCriteria.innerJoin("autor");
		easyCriteria.andStringLike(true, "autor.nome", "%" + name + "%");
		List<FraseEntidade> frasesEntidade = easyCriteria.getResultList();
		
		return frasesEntidade;
	}

}