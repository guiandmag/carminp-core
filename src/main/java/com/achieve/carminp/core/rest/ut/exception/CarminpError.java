package com.achieve.carminp.core.rest.ut.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Excpetion padrao para o mapeamento de 
 * erros que serao resultados do server side.
 * 
 * @author guilherme.magalhaes
 * @since 09/2014
 * @version 1.0
 */
@XmlRootElement(name = "carminpError")
public class CarminpError {
	private final String message;
    private final String stackTrace;
	
    public CarminpError(final Exception e) {
    	this(e.getMessage(), e);
    }
    
    public CarminpError(final String message, final Exception e) { 
    	this.message = message;

        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        stackTrace = sw.getBuffer().toString();
    }
    
    public String getMessage() {
        return message;
    }
    
    public String getStackTrace() {
        return stackTrace;
    }

	@Override
	public String toString() {
		return "CarminpError [message=" + message +" ]";
	}
}