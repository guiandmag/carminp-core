package com.achieve.carminp.core.business.in.service;

import java.util.List;

import javax.transaction.Transactional;

import com.achieve.carminp.core.model.im.entidade.FraseEntidade;
import com.achieve.carminp.core.model.in.dao.IGenericDAO;

/**
 * Define o contrato de acesso ao <b>DB</b>,
 * para a classe <code>FraseEntidade</code>.
 * 
 * @author guilherme.magalhaes
 * @since 09/2014
 * @version 2.0
 * @see IGenericDAO
 */
@Transactional
public interface IFraseService extends IGenericDAO<FraseEntidade> {

	/**
	 * Metodo pra recuperar tudo nas entidades no <b>DB</b> com as clausulas <code>OFFSET<> LIMIT<></code>.
	 * 
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<FraseEntidade> getPhrasesWithClauses(int offset, int limit);
	
	/**
	 * Metodo para recuperar as frases pelo id do autor desejado.
	 * 
	 * @param id
	 * @return
	 */
	List<FraseEntidade> getPhrasesByAuthorId(Long id);
	
	/**
	 * Metodo para recuperar as frases pelo nome do autor desejado.
	 * 
	 * @param name
	 * @return
	 */
	List<FraseEntidade> getPhrasesByAuthorName(String name);
	
	/**
	 * Metodo para recuperar todas frases, ja com o fetch join
	 * com a tbl_autor, evitando dessa forma round trip no <b>DB</b>.
	 * 
	 * @return
	 */
	List<FraseEntidade> getAllPhrases();
}