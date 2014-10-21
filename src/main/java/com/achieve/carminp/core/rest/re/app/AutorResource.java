/**
 * 
 */
package com.achieve.carminp.core.rest.re.app;

import java.net.URI;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.Status;

import com.achieve.carminp.core.business.in.service.IAutorService;
import com.achieve.carminp.core.model.im.entidade.AutorEntidade;

/**
 * Define o servico <b>REST</b> em que representara
 * um Autor no sistema.
 * 
 * @author guilherme.magalhaes
 * @since 09/2014
 * @version 1.0
 */
@RequestScoped
@Path("/autor")
public class AutorResource {
	
	@Inject
	IAutorService service;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response criarAutor(@Valid AutorEntidade autor, @Context HttpServletRequest req) {
		try {
			service.save(autor);
		} catch (Exception e) {
			throw new WebApplicationException(Status.CONFLICT);
		}
		
		URI uri = UriBuilder.fromPath("autor/{nome}").build(
						autor.getNome());
		
		return Response.created(uri).entity(autor).build();
	}
	
	@GET
	@Path("{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public AutorEntidade buscarAutorPorId(@PathParam("id")final Long idAutor) {
		AutorEntidade autorEncontrado = service.getById(idAutor);
		if (autorEncontrado != null)
			return autorEncontrado;
		
		throw new WebApplicationException(Status.NOT_FOUND);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AutorEntidade> buscarTodosAutores() {
		List<AutorEntidade> autoresEncontrados = service.findAll();
		
		return autoresEncontrados;
	}
	
	@DELETE
	@Path("{id:[0-9][0-9]*}")
	public void removerAutor(@PathParam("id")final Long idAutor) {
		service.delete(idAutor);
	}
}