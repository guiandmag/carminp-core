package com.achieve.carminp.core.business.im.service;

import javax.ejb.Stateless;

import com.achieve.carminp.core.business.in.service.IUsuarioService;
import com.achieve.carminp.core.model.ab.dao.GenericDAO;
import com.achieve.carminp.core.model.im.entidade.UsuarioEntidade;

/**
 * Classe que implementa as regras de servico definida nas interfaces 
 * para a implementacao dos requisitos necessitados para a transacao.
 * 
 * @author guilherme.magalhaes
 * @version 1.0
 * @see GenericDAO, {@link UsuarioEntidade}, {@link IUsuarioService}
 */
@Stateless
public class UsuarioService extends GenericDAO<UsuarioEntidade> implements 
		IUsuarioService {

}