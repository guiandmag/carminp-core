package com.achieve.carminp.core.business.in.service;

import javax.ejb.Local;

import com.achieve.carminp.core.model.im.entidade.UsuarioEntidade;
import com.achieve.carminp.core.model.in.dao.IGenericDAO;

/**
 * Define o contrato de acesso ao <b>DB</b>,
 * para a classe <code>UsuarioEntidade</code>.
 * 
 * @author guilherme.magalhaes
 * @since 10/2014
 * @version 1.0
 * @see UsuarioEntidade
 */
@Local
public interface IUsuarioService extends IGenericDAO<UsuarioEntidade> {

}