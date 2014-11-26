package com.achieve.carminp.core.rest.ge.in;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
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