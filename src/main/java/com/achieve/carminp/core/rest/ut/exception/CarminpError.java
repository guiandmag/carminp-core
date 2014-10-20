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
	private String message;
    private String stackTrace;
	
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
    public void setMessage(final String message) {
        this.message = message;
    }
    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(final String stackTrace) {
        this.stackTrace = stackTrace;
    }

	@Override
	public String toString() {
		return "CarminpError [message=" + message +" ]";
	}
}