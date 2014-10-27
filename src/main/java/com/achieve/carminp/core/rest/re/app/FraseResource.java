package com.achieve.carminp.core.rest.re.app;

import java.net.URI;
import java.util.List;

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
	
	@POST
	public Response criaFrase(@Valid final FraseEntidade frase, @Context HttpServletRequest req) {
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

	@GET
	public Response buscarTodasFrases() {
		List<FraseEntidade> frasesEcontradas = service.findAll();
		if(frasesEcontradas == null) {
			LOGGER.info("Frases nao encontradas");
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(frasesEcontradas).build();
	}
	
	@GET
	@Path("/autor/{nome}")
	public Response buscarFrasesPorAutorNome(@PathParam("nome")final String nomeAutor) {
		List<FraseEntidade> frasesEcontradas = service.getPhrasesByAuthorName(nomeAutor);
		
		if(frasesEcontradas == null) {
			LOGGER.info("Frases nao encontradas do seguinte autor {}", nomeAutor);
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(frasesEcontradas).build();
	}
	
	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response buscarFrasePorId(@PathParam("id")final Long idFrase) {
		FraseEntidade fraseEncontrada = service.getById(idFrase);
		if(fraseEncontrada == null) {
			LOGGER.info("Frases nao encontradas com seguinte id {}", idFrase);
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(fraseEncontrada).build();
	}
	
	@GET
	@Path("/autor/{id:[0-9][0-9]*}")
	public Response buscarFrasePorAutorId(@PathParam("id")final Long idAutor) {
		List<FraseEntidade> frasesEcontradas = service.getPhrasesByAuthorId(idAutor);
		
		if(frasesEcontradas == null) {
			LOGGER.info("Frases nao encontradas do seguinte id autor {}", idAutor);
			return Response.status(Status.NOT_FOUND).build();
		}
			
		return Response.ok(frasesEcontradas).build();
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