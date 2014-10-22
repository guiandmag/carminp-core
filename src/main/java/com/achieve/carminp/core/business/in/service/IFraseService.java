package com.achieve.carminp.core.business.in.service;

import java.util.List;

import javax.ejb.Local;

import com.achieve.carminp.core.model.im.entidade.FraseEntidade;
import com.achieve.carminp.core.model.in.dao.IGenericDAO;

/**
 * Define o contrato de acesso ao <b>DB</b>,
 * para a classe <code>FraseEntidade</code>.
 * 
 * @author guilherme.magalhaes
 * @since 09/2014
 * @version 1.0
 * @see FraseEntidade
 */
@Local
public interface IFraseService extends IGenericDAO<FraseEntidade> {

	/**
	 * Metodo para recuperar as frases pelo id do autor desejado.
	 * 
	 * @param id
	 * @return
	 */
	List<FraseEntidade> getPhrasesByAuthorId(Long id);
	
	/**
	 * Metodo para recuperar as frases pelo id do autor desejado.
	 * 
	 * @param name
	 * @return
	 */
	List<FraseEntidade> getPhrasesByAuthorName(String name);
	
}