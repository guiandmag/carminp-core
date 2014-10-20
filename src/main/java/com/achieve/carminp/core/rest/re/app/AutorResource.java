/**
 * 
 */
package com.achieve.carminp.core.rest.re.app;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	public void salvar(@Valid AutorEntidade autor) {
		 service.save(autor);
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public AutorEntidade buscarPorId(@PathParam("id") Long idAutor) {
		return service.getById(idAutor);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AutorEntidade> buscarTodosAutores() {
		return service.findAll();
	}
	
	@DELETE
	@Path("{id}")
	public void remover(@PathParam("id") Long idAutor) {
		service.delete(idAutor);
	}
}