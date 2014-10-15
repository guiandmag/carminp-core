package com.achieve.carminp.core.business.im.service;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;

import com.achieve.carminp.core.business.in.service.IFraseService;
import com.achieve.carminp.core.model.ab.dao.GenericDAO;
import com.achieve.carminp.core.model.im.entidade.FraseEntidade;

/**
 * Classe que implementa as regras de servico definida nas interfaces 
 * para a implementacao dos requisitos necessitados para a transacao.
 * 
 * @author guilherme.magalhaes
 * @version 1.0
 * @see GenericDAO, {@link FraseEntidade}, {@link IFraseService}
 * 
 */
@Stateless
public class FraseService extends GenericDAO<FraseEntidade> implements 
		IFraseService {

	@Override
	public void save(FraseEntidade entity) {
		save(entity);
	}

	@Override
	public void delete(Object id) throws EntityNotFoundException {
		delete(id);
	}

	@Override
	public FraseEntidade getById(Object id) throws EntityNotFoundException {
		return getById(id);
	}

	@Override
	public List<FraseEntidade> findAll() {
		return findAll();
	}

	@Override
	public List<FraseEntidade> findByFields(Map<String, Object> fields,
			Boolean exclusive, int maxResults, String orderBy) {
		return findByFields(fields, exclusive, maxResults, orderBy);
	}

}
