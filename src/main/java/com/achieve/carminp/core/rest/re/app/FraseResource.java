package com.achieve.carminp.core.rest.re.app;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
	
	
	@Inject
	IFraseService service;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response atualizar(final FraseEntidade frase, HttpServletRequest req) {
		service.save(frase);
		
		return Response.status(Status.OK).build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response buscarTodos() {
		final List<FraseEntidade> frasesEcontradas = service.getAllPhrases();
		
		if(frasesEcontradas == null)
			throw new NotFoundException();
		
		return Response.ok(frasesEcontradas).build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response buscarTodosComClausulas(final int start, final int size){
		List<FraseEntidade> frasesEcontradas = service.getPhrasesWithClauses(start, size);
		
		if(frasesEcontradas == null)
			throw new NotFoundException();
		
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
			throw new NotFoundException();
		
		return Response.status(Status.OK).build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response buscarFrasePorAutorId(final Long idAutor) {
		List<FraseEntidade> frasesEcontradas = service.getPhrasesByAuthorId(idAutor);
		
		if(frasesEcontradas == null) 
			throw new NotFoundException();
			
		return Response.ok(frasesEcontradas).build();
	}
	
}