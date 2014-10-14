package com.achieve.carminp.core.model.im.entidade;

import java.lang.Long;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.*;

import com.achieve.carminp.core.model.in.entidade.IEntity;

/**
 * Entidade para representar o autor da frase a ser salva.
 *
 * @author guilherme.magalhaes
 * @version 1.0
 * @see IEntity
 */
@Entity
@Table(name = "TBL_AUTOR")
public class AutorEntidade implements IEntity<Long> {

	private static final long serialVersionUID = 1L;
	   
	@Id
	private Long id;
	
	@Column
	private String nome;
	
	@ManyToOne
	private Set<FraseEntidade> frases = new LinkedHashSet<FraseEntidade>();

	public AutorEntidade() {
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