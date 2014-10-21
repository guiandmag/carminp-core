package com.achieve.carminp.core.business.im.service;

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
 * @see GenericDAO, {@link FraseEntidade}, {@link IFraseService}
 */
@Stateless
public class FraseService extends GenericDAO<FraseEntidade> implements 
		IFraseService {

	@Override
	public FraseEntidade getPhrasesByAuthorId(Long id) {
		EasyCriteria<FraseEntidade> easyCriteria = EasyCriteriaFactory.createQueryCriteria(em, FraseEntidade.class);
		FraseEntidade fraseEntidade = easyCriteria.andEquals("autor_id", id).getSingleResult();
		
		return fraseEntidade;
	}

}