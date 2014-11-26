package com.achieve.carminp.core.rest.re.in;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Interface para a apresentacao das regras a 
 * serem implementadas pelas classes de autor da
 * API do <b>REST</b>.
 * 
 * @author guilherme.magalhaes
 * @since 11/2014
 * @version 1.1
 */
@Path("/autor")
public interface IAutorResource {

	/**
	 * Metodo para recuperar o autor pelo seu 
	 * nome na API do <b>REST</b>
	 * 
	 * @param nomeAutor
	 * @return
	 */
	@GET
	@Path("/{nome}")
	public Response buscarAutorPorNome(@PathParam("nome")final String nomeAutor);
	
}