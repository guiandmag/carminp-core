package com.achieve.carminp.core.rest.re.app;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.achieve.carminp.core.business.in.service.IFraseService;
import com.achieve.carminp.core.model.im.entidade.FraseEntidade;
import com.achieve.carminp.core.rest.ge.in.IGenericRest;
import com.achieve.carminp.core.rest.re.in.IFraseResource;

/**
 * Define o servico <b>REST</b> em que representara
 * uma Frase no sistema.
 * 
 * @author guilherme.magalhaes
 * @since 09/2014
 * @version 1.1
 * @see IGenericRest, {@link IFraseResource}
 */
@Path("/frase")
public class FraseResource implements IGenericRest<FraseEntidade>,
		IFraseResource{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FraseResource.class.getName());
	
	@Inject
	IFraseService service;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response criar(final FraseEntidade frase, HttpServletRequest req) {
		try {
			service.save(frase);
		} catch (Exception e) {
			LOGGER.error("Erro encontrado {}", e);
			throw new WebApplicationException(Status.EXPECTATION_FAILED);
		}
		
		URI uri = UriBuilder.fromPath("frase/{frase}").build(
					frase.getFrase());
		
		return Response.created(uri).entity(frase).build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response buscarPorId(final Long id) {
		FraseEntidade fraseEncontrada = service.getById(id);
		
		if(fraseEncontrada == null) 
			return Response.status(Status.NOT_FOUND).build();
		
		return Response.ok(fraseEncontrada).build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response buscarTodos() {
		List<FraseEntidade> frasesEcontradas = service.findAll();
		if(frasesEcontradas == null)
			return Response.status(Status.NOT_FOUND).build();
		
		return Response.ok(frasesEcontradas).build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response remover(final Long id) {
		if(id != null) 
			service.delete(id);
		else 
			LOGGER.info("Frase com id {} não existe e, portanto, nada foi excluído", id);
		
		return Response.status(Status.OK).build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response buscarFrasesPorAutorNome(final String nomeAutor) {
		List<FraseEntidade> frasesEcontradas = service.getPhrasesByAuthorName(nomeAutor);
		
		if(frasesEcontradas == null) {
			LOGGER.info("Frases nao encontradas do seguinte autor {}", nomeAutor);
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(frasesEcontradas).build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response buscarFrasePorAutorId(final Long idAutor) {
		List<FraseEntidade> frasesEcontradas = service.getPhrasesByAuthorId(idAutor);
		
		if(frasesEcontradas == null) {
			LOGGER.info("Frases nao encontradas do seguinte id autor {}", idAutor);
			return Response.status(Status.NOT_FOUND).build();
		}
			
		return Response.ok(frasesEcontradas).build();
	}
	
}