package com.achieve.carminp.core.model.im.entidade;

import java.lang.Long;

import javax.persistence.*;
import javax.validation.Valid;
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
@XmlRootElement(name = "frase")
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
	@Valid
	@XmlElementWrapper
	private AutorEntidade autor;
	
	
	public FraseEntidade() {
		super();
	}   
	
	public FraseEntidade(Long id, String frase, AutorEntidade autor) {
		super();
		this.id = id;
		this.frase = frase;
		this.autor = autor;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public Long getId() {
		return id;
	}

	public String getFrase() {
		return frase;
	}

	public void setFrase(String frase) {
		this.frase = frase;
	}

	public AutorEntidade getAutor() {
		return autor;
	}

	public void setAutor(AutorEntidade autor) {
		this.autor = autor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((frase == null) ? 0 : frase.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FraseEntidade other = (FraseEntidade) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (frase == null) {
			if (other.frase != null)
				return false;
		} else if (!frase.equals(other.frase))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FraseEntidade [id=" + id + ", frase=" + frase + ", autor="
				+ autor + "]";
	}

}