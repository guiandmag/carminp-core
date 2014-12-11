package com.achieve.carminp.core.business.im.service;

import java.util.List;

import javax.ejb.Stateless;

import com.achieve.carminp.core.business.in.service.IAutorService;
import com.achieve.carminp.core.model.ab.dao.GenericDAO;
import com.achieve.carminp.core.model.im.entidade.AutorEntidade;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

/**
 * Classe que implementa as regras de servico definida nas interfaces 
 * para a implementacao dos requisitos necessitados para a transacao.
 * 
 * @author guilherme.magalhaes
 * @version 1.1
 * @see GenericDAO, {@link AutorEntidade}, {@link IAutorService}
 */
@Stateless
public class AutorService extends GenericDAO<AutorEntidade> implements
		IAutorService {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AutorEntidade> getAllAuthorOrderById() {
		final UaiCriteria<AutorEntidade> uaiCriteria = UaiCriteriaFactory.createQueryCriteria(em, AutorEntidade.class);
		uaiCriteria.orderByAsc("id");
		
		final List<AutorEntidade> autoresEncontrados = uaiCriteria.getResultList();
		
		return autoresEncontrados;
	}

}