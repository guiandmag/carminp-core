package com.achieve.carminp.core.business.in.service;

import java.util.List;

import javax.transaction.Transactional;

import com.achieve.carminp.core.model.im.entidade.AutorEntidade;
import com.achieve.carminp.core.model.in.dao.IGenericDAO;

/**
 * Define o contrato de acesso ao <b>DB</b>,
 * para a classe <code>AutorEntidade</code>.
 * 
 * @author guilherme.magalhaes
 * @since 09/2014
 * @version 2.0
 * @see IGenericDAO
 */
@Transactional
public interface IAutorService extends IGenericDAO<AutorEntidade> {
	
	/**
	 * Metodo para recuperar autor pelo <b>ID</b>.
	 * 
	 * @param id
	 * @return
	 */
	AutorEntidade getAuthorById(Long id);
	
	/**
	 * Metodo para retornar somente o autor, sem suas frases.
	 * 
	 * @return
	 */
	List<AutorEntidade> getOnlyAuthor();
	
	/**
	 * Metodo para recuperar somente o autor pelo seu nome.
	 * 
	 * @param name
	 * @return
	 */
	List<AutorEntidade> getOnlyAuthorByName(String name);
	
	/**
	 * Metodo para recuperar somente o autor pelo seu nome.
	 * 
	 * @param name
	 * @return
	 */
	List<AutorEntidade> getAuthorByNameWithPhrases(String name);
}