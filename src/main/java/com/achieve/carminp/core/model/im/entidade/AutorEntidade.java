package com.achieve.carminp.core.model.im.entidade;

import java.lang.Long;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.achieve.carminp.core.model.in.entidade.IEntity;

/**
 * Entidade para representar o autor da frase a ser salva.
 *
 * @author guilherme.magalhaes
 * @version 1.0
 * @see IEntity
 */
@Entity
@Table(name = "TBL_AUTOR", indexes = { @Index(columnList = "autor_nome") })
@SequenceGenerator(name = AutorEntidade.AUTOR_SEQUENCIA, sequenceName = AutorEntidade.AUTOR_SEQUENCIA, initialValue = 1, allocationSize = 50)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AutorEntidade implements IEntity<Long> {

	private static final long serialVersionUID = 1L;
	
	@Transient
	public static final String AUTOR_SEQUENCIA = "AUTOR_SEQUENCIA";
	   
	@Id
	@GeneratedValue(generator = AUTOR_SEQUENCIA, strategy = GenerationType.SEQUENCE)
	@Column(name = "autor_id", updatable = false, unique = true, nullable = false)
	@XmlAttribute
	private Long id;
	
	@Column(name = "autor_nome", nullable = false, length = 50)
	@XmlElement
	private String nome;
	
	@OneToMany(mappedBy = "autor", orphanRemoval = true)
	@XmlElementWrapper
	private FraseEntidade frases;

	public AutorEntidade() {
		super();
	}   
	
	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public Long getId() {
		return id;
	}

}