package com.achieve.carminp.core.model.im.entidade;

import java.lang.Long;

import javax.persistence.*;
import javax.validation.Valid;

import com.achieve.carminp.core.model.in.entidade.IEntity;

/**
 * Entidade para representar a frase a ser salva no DB.
 *
 * @author guilherme.magalhaes
 * @version 1.0
 * @see IEntity
 */
@Entity
@Table(name = "TBL_FRASE")
public class FraseEntidade implements IEntity<Long> {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	@Column
	private String frase;
	
	@Valid
	@OneToMany
	private AutorEntidade autor;
	

	public FraseEntidade() {
		super();
	}   
	
	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public Long getId() {
		return this.id;
	}

}