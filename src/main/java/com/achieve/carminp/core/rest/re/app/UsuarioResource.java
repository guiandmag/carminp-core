package com.achieve.carminp.core.rest.re.app;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.achieve.carminp.core.business.in.service.IUsuarioService;
import com.achieve.carminp.core.model.im.entidade.UsuarioEntidade;
import com.achieve.carminp.core.rest.re.in.IUsuarioResource;

/**
 * Define o servico <b>REST</b> em que representara
 * um Usuario no sistema.
 * 
 * @author guilherme.magalhaes
 * @since 10/2014
 * @version 1.1
 * @see IUsuarioResource
 */
public class UsuarioResource implements IUsuarioResource{

	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioEntidade.class.getName());
	
	@Inject
	IUsuarioService service;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response criar(final UsuarioEntidade usuario, HttpServletRequest req) {
		try {
			service.save(usuario);
		} catch (Exception e) {
			LOGGER.error("Erro encontrado {}", e);
			throw new WebApplicationException(Status.EXPECTATION_FAILED);
		}
		
		URI uri = UriBuilder.fromPath("usuario/{nome}").build(
						usuario.getNomeUsuario());
		
		return Response.created(uri).entity(usuario).build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response atualizar(final UsuarioEntidade usuario, HttpServletRequest req) {
		service.update(usuario);
		
		return Response.status(Status.OK).build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response buscarPorId(final Long id) {
		UsuarioEntidade usuarioEncontrado = service.getById(id);
		
		if (usuarioEncontrado == null)
			return Response.status(Status.NOT_FOUND).build();
		
		return Response.ok(usuarioEncontrado).build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response buscarTodos() {
		final List<UsuarioEntidade> usuarioEncontrados = service.findAll();
		
		if(usuarioEncontrados == null) 
			return Response.status(Status.NOT_FOUND).build();
		
		return Response.ok(usuarioEncontrados).build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response buscarTodosComClausulas(final int start, final int size){
		final List<UsuarioEntidade> usuarioEncontrados = service.findAllWithClauses(start, size);
		
		if(usuarioEncontrados == null) 
			return Response.status(Status.NOT_FOUND).build();
		
		return Response.ok(usuarioEncontrados).build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response remover(final Long id) {
		if (id != null) 
			service.delete(id);
		else 
			LOGGER.info("Id {} nulo, portanto, informe um valido", id);
		
		return Response.status(Status.OK).build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response buscarUsuarioPorNome(final String nome) {
		Map<String, Object> field = new HashMap<String, Object>();
		field.put("nome", nome);
		
		List<UsuarioEntidade> usuariosEncontrados = service.findByFields(field, true, 0, null);
		
		if(usuariosEncontrados == null) 
			return Response.status(Status.NOT_FOUND).build();
		
		return Response.ok(usuariosEncontrados).build();
	}

}