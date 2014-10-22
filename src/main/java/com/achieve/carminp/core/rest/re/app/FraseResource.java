package com.achieve.carminp.core.rest.re.app;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.achieve.carminp.core.business.in.service.IFraseService;
import com.achieve.carminp.core.model.im.entidade.FraseEntidade;

/**
 * Define o servico <b>REST</b> em que representara
 * uma Frase no sistema.
 * 
 * @author guilherme.magalhaes
 * @since 09/2014
 * @version 1.0
 */
@Path("/frase")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FraseResource {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FraseResource.class.getName());
	
	@Inject
	IFraseService service;

	@GET
	public List<FraseEntidade> buscarTodasFrases() {
		List<FraseEntidade> frasesEcontradas = service.findAll();
		
		return frasesEcontradas;
	}
	
	@GET
	@Path("/autor/{nome}")
	public List<FraseEntidade> buscarFrasesPorAutorNome(@PathParam("nome") String nomeAutor) {
		List<FraseEntidade> frasesEcontradas = service.getPhrasesByAuthorName(nomeAutor);
		
		if(frasesEcontradas == null) 
			LOGGER.info("Frases nao encontradas do seguinte autor {}", nomeAutor);
		
		return frasesEcontradas;
	}
	
	@GET
	@Path("/{id:[0-9][0-9]*}")
	public FraseEntidade buscarFrasePorId(@PathParam("id") Long idFrase) {
		FraseEntidade fraseEncontrada = service.getById(idFrase);
		if(fraseEncontrada != null)
			return fraseEncontrada;
		
		throw new WebApplicationException(Status.NOT_FOUND);
	}
	
	@GET
	@Path("/autor/{id:[0-9][0-9]*}")
	public List<FraseEntidade> buscarFrasePorAutorId(@PathParam("id") Long idAutor) {
		List<FraseEntidade> frasesEcontradas = service.getPhrasesByAuthorId(idAutor);
		
		if(frasesEcontradas == null) 
			LOGGER.info("Frases nao encontradas do seguinte id autor {}", idAutor);
			
		return frasesEcontradas;
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public void removeFrase(@PathParam("id") final Long idFrase) {
		if(idFrase != null) {
			LOGGER.info("Removendo frase com o id {}", idFrase);
			service.delete(idFrase);
		} else {
			LOGGER.info("Frase com id {} não existe e, portanto, nada foi excluído", idFrase);
		}
	}
}