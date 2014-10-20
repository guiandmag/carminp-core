package com.achieve.carminp.core.rest.ut.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Mapeador de exceptions que ira tratar todas
 * as falhas com status 500 e retornar uma mensagem
 * do tipo <code>CarminpError</code>, contendo o detalhe
 * do erro.
 * 
 * @author guilherme.magalhaes
 * @since 09/2014
 * @version 1.0
 * @see CarminpError
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(final Exception e) {
		return Response.status(500)
				.entity(new CarminpError(e))
				.build();
	}

}