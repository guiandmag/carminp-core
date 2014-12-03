package com.achieve.carminp.core.rest.re.in;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.achieve.carminp.core.model.im.entidade.FraseEntidade;

/**
 * Interface para a apresentacao das regras a 
 * serem implementadas pelas classes de frase da
 * API do <b>REST</b>.
 * 
 * @author guilherme.magalhaes
 * @since 11/2014
 * @version 1.1
 */
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/frase")
public interface IFraseResource {
	
	/**
	 * Metodo para a atualizacao da frase no sistema
	 * com a interface <b>REST</b>.
	 * 
	 * @param entity
	 * @param req
	 * @return
	 */
	@PUT
	Response atualizar(FraseEntidade frase, HttpServletRequest req);
	
	/**
	 * Metodo para retornar todas as frases do DB
	 * para a apresentacao pela camada <b>REST</b>
	 * 
	 * @return
	 */
	@GET
	Response buscarTodos();
	
	/**
	 * Metodo para retornar todas as frases do DB 
	 * com as clausulas de paginacao
	 * para a apresentacao pela camada <b>REST</b>
	 * 
	 * @param size
	 * @param start
	 * @return
	 */
	@GET
	@Path("/pag")
	Response buscarTodosComClausulas(@QueryParam("start") int start, @QueryParam("size") int size);
	
	/**
	 * Metodo para remover a frase pelo seu <code>id</code> fornecido
	 * na API do <b>REST</b>
	 * 
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	Response remover(Long id);
	
	/**
	 * Metodo para recuperar as frases pelo 
	 * nome do autor na API de <b>REST</b>.
	 * 
	 * @param nomeAutor
	 * @return
	 */
	@GET
	@Path("/autor/{nome}")
	Response buscarFrasesPorAutorNome(@PathParam("nome") String nomeAutor);
	
	/**
	 * Metodo para recuperar as frases pelo
	 * id do autor na API de <b>REST</b>.
	 * 
	 * @param idAutor
	 * @return
	 */
	@GET
	@Path("/autor/{id:[0-9][0-9]*}")
	Response buscarFrasePorAutorId(@PathParam("id") Long idAutor);

}