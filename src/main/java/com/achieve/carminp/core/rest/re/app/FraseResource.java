package com.achieve.carminp.core.rest.re.app;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.achieve.carminp.core.business.in.service.IFraseService;
import com.achieve.carminp.core.model.im.entidade.FraseEntidade;
import com.achieve.carminp.core.rest.re.in.IFraseResource;

/**
 * Define o servico <b>REST</b> em que representara
 * uma Frase no sistema.
 * 
 * @author guilherme.magalhaes
 * @since 09/2014
 * @version 1.1
 * @see IFraseResource
 */
public class FraseResource implements IFraseResource{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FraseResource.class.getName());
	
	@Inject
	IFraseService service;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response atualizar(final FraseEntidade frase, HttpServletRequest req) {
		service.update(frase);
		
		return Response.status(Status.OK).build();
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
	public Response buscarTodosComClausulas(final int start, final int size){
		List<FraseEntidade> frasesEcontradas = service.findAllWithClauses(start, size);
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
			LOGGER.info("Id {} nulo, portanto, informe um valido", id);
		
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