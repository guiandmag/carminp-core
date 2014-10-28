package com.achieve.carminp.core.model.im.rest;

import java.net.URI;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.achieve.carminp.core.business.in.service.IUsuarioService;
import com.achieve.carminp.core.model.im.entidade.UsuarioEntidade;

/**
 * Define o servico <b>REST</b> em que representara
 * um Usuario no sistema.
 * 
 * @author guilherme.magalhaes
 * @since 10/2014
 * @version 1.0
 */
@RequestScoped
@Path("/usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioEntidade.class.getName());
	
	@Inject
	IUsuarioService service;
	
	@POST
	public Response criarUsuario(@Valid final UsuarioEntidade usuario, @Context HttpServletResponse res) {
		try {
			service.save(usuario);
		} catch (Exception e) {
			throw new WebApplicationException(Status.EXPECTATION_FAILED);
		}
		
		URI uri = UriBuilder.fromPath("usuario/{nome}").build(
						usuario.getNomeUsuario());
		
		return Response.created(uri).entity(usuario).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response buscarAutorPorId(@PathParam("id") final Long idUsuario) {
		UsuarioEntidade usuarioEncontrado = service.getById(idUsuario);
		if (usuarioEncontrado == null) {
			LOGGER.debug("Usuario com o id {}, nao encontrado", idUsuario);
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(usuarioEncontrado).build();
	}

	@GET
	public Response buscarTodosUsuarios() {
		final List<UsuarioEntidade> usuarioEncontrados = service.findAll();
		if(usuarioEncontrados == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(usuarioEncontrados).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public void deleteById(@PathParam("id") final Long idUsuario) {
		if (idUsuario != null) {
			LOGGER.info("Removendo usuario com id {}", idUsuario);
			service.delete(idUsuario);
		} else {
			LOGGER.info("Usuario com id {} não existe e, portanto, nada foi excluído", idUsuario);
		}
	}

}