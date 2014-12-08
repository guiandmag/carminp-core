package com.achieve.carminp.core.rest.ge.in;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.achieve.carminp.core.model.in.entidade.IEntity;

/**
 * Define o contrato de acesso a camada de servicos <b>REST</b>
 * Todas a classes que o implementarem deveram sobreescrever seus metodos
 * e ter uma tipagem que herde de <code>IEntity</code>, seguindo assim
 * nossa convencao para o Generics.
 * 
 * @author guilherme.magalhaes
 * @since 11/2014
 * @version 1.1
 * @param <T>
 * @see IEntity
 */
public interface IGenericRest<T extends IEntity<?>> {
	
	/**
	 * Metodo para a criacao do usuario no sistema 
	 * com a interface <b>REST</b>.
	 * 
	 * @param entity
	 * @param req
	 * @return
	 */
	@POST
	Response criar(@Valid T entity, @Context HttpServletRequest req);
	
	/**
	 * Metodo para a atualizacao de entidade no sistema
	 * com a interface <b>REST</b>.
	 * 
	 * @param entity
	 * @param req
	 * @return
	 */
	@PUT
	Response atualizar(@Valid T entity, @Context HttpServletRequest req);
	
	/**
	 * Metodo para retornar a entidade pelo seu <code>id</code> fornecido
	 * na API do <b>REST</b>.
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id:[0-9][0-9]*}")
	Response buscarPorId(@PathParam("id") Long id);

	/**
	 * Metodo para retornar todas as entidades do DB
	 * para a apresentacao pela camada <b>REST</b>
	 * 
	 * @return
	 */
	@GET
	Response buscarTodos();
	
	/**
	 * Metodo para retornar todas as entidades do DB 
	 * com as clausulas de paginacao
	 * para a apresentacao pela camada <b>REST</b>
	 * 
	 * @param size
	 * @param start
	 * @return
	 */
	@GET
	@Path("/pag")
	Response buscarTodosComClausulas(@QueryParam("start") int size, @DefaultValue("5") @QueryParam("size") int start);
	
	/**
	 * Metodo para remover a entidade pelo seu <code>id</code> fornecido
	 * na API do <b>REST</b>
	 * 
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	Response remover(@PathParam("id") Long id);
	
}