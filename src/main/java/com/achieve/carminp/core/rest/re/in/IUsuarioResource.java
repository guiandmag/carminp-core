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
 * serem implementadas pelas classes de usuario da
 * API do <b>REST</b>.
 * 
 * @author guilherme.magalhaes
 * @since 11/2014
 * @version 1.1
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IUsuarioResource {

	/**
	 * Metodo para recuperar o usuario pelo 
	 * nome na API do <b>REST</b>
	 * 
	 * @param nome
	 * @return
	 */
	@GET
	@Path("/{nome}")
	Response buscarUsuarioPorNome(@PathParam("nome") String nome);
	
}