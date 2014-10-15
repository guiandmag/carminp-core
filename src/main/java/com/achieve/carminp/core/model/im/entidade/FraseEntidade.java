package com.achieve.carminp.core.model.im.entidade;

import java.lang.Long;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

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
@SequenceGenerator(name = FraseEntidade.FRASE_SEQUENCIA, sequenceName = FraseEntidade.FRASE_SEQUENCIA, initialValue = 1, allocationSize = 50)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FraseEntidade implements IEntity<Long> {

	private static final long serialVersionUID = 1L;
	
	@Transient
	public static final String FRASE_SEQUENCIA = "FRASE_SEQUENCIA";
	
	@Id
	@GeneratedValue(generator = FRASE_SEQUENCIA, strategy = GenerationType.SEQUENCE)
	@Column(name = "frase_id", updatable = false, unique = true, nullable = false)
	@XmlAttribute
	private Long id;
	
	@Column(name = "frase_frase", nullable = false, length = 244)
	@XmlElement
	private String frase;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "autor_id", nullable = false)
	@XmlElementWrapper
	private Set<AutorEntidade> autor = new LinkedHashSet<AutorEntidade>();
	

	public FraseEntidade() {
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