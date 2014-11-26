package com.achieve.carminp.core.rest.re.in;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Interface para a apresentacao das regras a 
 * serem implementadas pelas classes de frase da
 * API do <b>REST</b>.
 * 
 * @author guilherme.magalhaes
 * @since 11/2014
 * @version 1.1
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IFraseResource {
	
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