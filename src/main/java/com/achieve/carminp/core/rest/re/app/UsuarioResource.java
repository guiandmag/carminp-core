package com.achieve.carminp.core.rest.re.app;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;
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
 * @version 2.0
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
			throw new NotSupportedException();
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
		service.save(usuario);
		
		return Response.status(Status.OK).build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response buscarPorId(final Long id) {
		UsuarioEntidade usuarioEncontrado = service.getUserById(id);
		
		if (usuarioEncontrado == null)
			throw new NotFoundException();
		
		return Response.ok(usuarioEncontrado).build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response buscarTodos() {
		final List<UsuarioEntidade> usuarioEncontrados = service.getOnlyUser();
		
		if(usuarioEncontrados == null) 
			throw new NotFoundException();
		
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
			throw new NotFoundException();
		
		return Response.status(Status.OK).build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response buscarUsuarioPorNome(final String nome) {
		List<UsuarioEntidade> usuariosEncontrados = service.getUsersByName(nome);
		
		if(usuariosEncontrados == null) 
			throw new NotFoundException();
		
		return Response.ok(usuariosEncontrados).build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response buscarUsuarioEFavoritoPorNomeUsuario() {
		List<UsuarioEntidade> usuariosEncontrados = service.getUserAndFavoritePhrases();
		
		if(usuariosEncontrados == null) 
			throw new NotFoundException();
		
		return Response.ok(usuariosEncontrados).build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response buscarUsuarioEFavoritoPorNomeUsuarioComClausulas(int start, int size) {
		List<UsuarioEntidade> usuariosEncontrados = service.getUserAndFavoritePhrasesWithClause(start, size);
		
		if(usuariosEncontrados == null) 
			throw new NotFoundException();
		
		return Response.ok(usuariosEncontrados).build();
	}

}