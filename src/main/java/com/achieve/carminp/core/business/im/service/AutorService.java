package com.achieve.carminp.core.business.im.service;

import java.util.List;

import javax.ejb.Stateless;

import com.achieve.carminp.core.business.in.service.IAutorService;
import com.achieve.carminp.core.model.ab.dao.GenericDAO;
import com.achieve.carminp.core.model.im.entidade.AutorEntidade;
import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

/**
 * Classe que implementa as regras de servico definida nas interfaces 
 * para a implementacao dos requisitos necessitados para a transacao.
 * 
 * @author guilherme.magalhaes
 * @version 1.0
 * @see GenericDAO, {@link AutorEntidade}, {@link IAutorService}
 */
@Stateless
public class AutorService extends GenericDAO<AutorEntidade> implements
		IAutorService {

	@Override
	public List<AutorEntidade> getAllAuthorOrderById() {
		EasyCriteria<AutorEntidade> easyCriteria = EasyCriteriaFactory.createQueryCriteria(em, AutorEntidade.class);
		easyCriteria.orderByAsc("id");
		
		List<AutorEntidade> autoresEncontrados = easyCriteria.getResultList();
		
		return autoresEncontrados;
	}

}