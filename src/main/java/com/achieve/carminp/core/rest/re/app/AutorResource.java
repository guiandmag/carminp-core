package com.achieve.carminp.core.rest.re.app;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.achieve.carminp.core.business.in.service.IAutorService;
import com.achieve.carminp.core.model.im.entidade.AutorEntidade;
import com.achieve.carminp.core.rest.re.in.IAutorResource;

/**
 * Define o servico <b>REST</b> em que representara
 * um Autor no sistema.
 * 
 * @author guilherme.magalhaes
 * @since 09/2014
 * @version 1.1
 * @see IAutorResource
 */
public class AutorResource implements IAutorResource{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AutorResource.class.getName());
	
	@Inject
	IAutorService service;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response criar(final AutorEntidade autor, HttpServletRequest req) {
		try {
			service.save(autor);
		} catch (Exception e) {
			LOGGER.error("Erro encontrado {}", e);
			throw new NotSupportedException();
		}
		
		URI uri = UriBuilder.fromPath("autor/{nome}").build(
						autor.getNome(), false);
		
		return Response.created(uri).entity(autor).build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response atualizar(final AutorEntidade autor, HttpServletRequest req) {
		service.save(autor);
		
		return Response.status(Status.OK).build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response buscarPorId(final Long id) {
		AutorEntidade autorEncontrado = service.getById(id);
		
		if (autorEncontrado == null) 
			throw new NotFoundException();
			
		return Response.ok(autorEncontrado).build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response buscarTodos() {
		List<AutorEntidade> autoresEncontrados = service.findAll();
		
		if(autoresEncontrados == null) 
			throw new NotFoundException();
		
		return Response.ok(autoresEncontrados).build();	
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
	public Response buscarAutorPorNome(final String nomeAutor) {
		Map<String, Object> field = new HashMap<String, Object>();
		field.put("nome", nomeAutor);
		
		List<AutorEntidade> autoresEncontrados = service.findByFields(field, true, 0, null);
		
		if(autoresEncontrados == null) 
			throw new NotFoundException();
		
		return Response.ok(autoresEncontrados).build();
	}
	
}