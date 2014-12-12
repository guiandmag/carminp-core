package com.achieve.carminp.core.business.in.service;

import java.util.List;

import javax.ejb.Local;

import com.achieve.carminp.core.model.im.entidade.UsuarioEntidade;
import com.achieve.carminp.core.model.in.dao.IGenericDAO;

/**
 * Define o contrato de acesso ao <b>DB</b>,
 * para a classe <code>UsuarioEntidade</code>.
 * 
 * @author guilherme.magalhaes
 * @since 10/2014
 * @version 2.0
 * @see UsuarioEntidade
 */
@Local
public interface IUsuarioService extends IGenericDAO<UsuarioEntidade> {

	/**
	 * Metodo para recuperar o usuario pelo seu <b>ID</b>.
	 * 
	 * @param id
	 * @return
	 */
	UsuarioEntidade getUserById(Long id);
	
	/**
	 * Metodo para recuperar somente o usuario,
	 * sem suas entidades relacionadas.
	 * 
	 * @return
	 */
	List<UsuarioEntidade> getOnlyUser();
	
	/**
	 * Metodo para recuperar o usuario e seus favoritos.
	 * 
	 * @return
	 */
	List<UsuarioEntidade> getUserAndFavoritePhrases();
	
	/**
	 * Metodo para recuperar o usuario e seus favoritos
	 * com clausulas de paginacao.
	 * 
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<UsuarioEntidade> getUserAndFavoritePhrasesWithClause(int offset, int limit);
	
	/**
	 * Metodo para retornar somente o usuario,
	 * sem suas entidades relacionadas pelo seu nome.
	 * 
	 * @param name
	 * @return
	 */
	List<UsuarioEntidade> getOnlyUserByName(String name);
	
	/**
	 * Metodo para recuperar usuario pelo seu nome.
	 * 
	 * @param name
	 * @return
	 */
	List<UsuarioEntidade> getUsersByName(String name);
	
}