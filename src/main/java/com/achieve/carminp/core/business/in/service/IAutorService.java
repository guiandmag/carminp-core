package com.achieve.carminp.core.business.in.service;

import java.util.List;

import javax.ejb.Local;

import com.achieve.carminp.core.model.im.entidade.AutorEntidade;
import com.achieve.carminp.core.model.in.dao.IGenericDAO;

/**
 * Define o contrato de acesso ao <b>DB</b>,
 * para a classe <code>AutorEntidade</code>.
 * 
 * @author guilherme.magalhaes
 * @since 09/2014
 * @version 1.0
 * @see AutorEntidade
 */
@Local
public interface IAutorService extends IGenericDAO<AutorEntidade> {
	
	List<AutorEntidade> getMostAuthorsWithPhrases();

}