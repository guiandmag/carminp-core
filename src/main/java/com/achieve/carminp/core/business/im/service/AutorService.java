package com.achieve.carminp.core.business.im.service;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;

import com.achieve.carminp.core.business.in.service.IAutorService;
import com.achieve.carminp.core.model.ab.dao.GenericDAO;
import com.achieve.carminp.core.model.im.entidade.AutorEntidade;

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
	public void save(AutorEntidade entity) {
		save(entity);
	}

	@Override
	public void delete(Object id) throws EntityNotFoundException {
		delete(id);
	}

	@Override
	public AutorEntidade getById(Object id) throws EntityNotFoundException {
		return getById(id);
	}

	@Override
	public List<AutorEntidade> findAll() {
		return findAll();
	}

	@Override
	public List<AutorEntidade> findByFields(Map<String, Object> fields,
			Boolean exclusive, int maxResults, String orderBy) {
		return findByFields(fields, exclusive, maxResults, orderBy);
	}

	@Override
	public List<AutorEntidade> getMostAuthorsWithPhrases() {
		return null;
	}

}
