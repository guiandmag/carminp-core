package com.achieve.carminp.core.rest.re.app;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
@Path("/autor")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AutorResource {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AutorResource.class.getName());
	
	@Inject
	IAutorService service;

	@POST
	public Response criarAutor(@Valid AutorEntidade autor, @Context HttpServletRequest req) {
		try {
			service.save(autor);
		} catch (Exception e) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		
		URI uri = UriBuilder.fromPath("autor/{nome}").build(
						autor.getNome());
		
		return Response.created(uri).entity(autor).build();
	}
	
	@GET
	@Path("/{id:[0-9][0-9]*}")
	public AutorEntidade buscarAutorPorId(@PathParam("id") Long idAutor) {
		AutorEntidade autorEncontrado = service.getById(idAutor);
		if (autorEncontrado != null) 
			return autorEncontrado;
		
		throw new WebApplicationException(Status.NOT_FOUND);
	}
	
	@GET
	@Path("/{nome}")
	public List<AutorEntidade> buscarAutorPorNome(@PathParam("nome") String nomeAutor) {
		Map<String, Object> field = new HashMap<String, Object>();
		field.put("nome", "like '%" + nomeAutor + "%'");
		
		List<AutorEntidade> autoresEncontrados = service.findByFields(field, true, 0, null);
		
		return autoresEncontrados;
	}
	
	@GET
	public List<AutorEntidade> buscarTodosAutores() {
		List<AutorEntidade> autoresEncontrados = service.findAll();
		
		return autoresEncontrados;
	}
	
	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public void removerAutor(@PathParam("id") Long idAutor) {
		if (idAutor != null) {
			LOGGER.info("Removendo autor com id {}", idAutor);
			service.delete(idAutor);
		} else {
			LOGGER.info("Autor com id {} não existe e, portanto, nada foi excluído", idAutor);
		}
	}
}