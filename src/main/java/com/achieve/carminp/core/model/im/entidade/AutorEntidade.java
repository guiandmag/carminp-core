package com.achieve.carminp.core.model.im.entidade;

import java.lang.Long;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.achieve.carminp.core.model.in.entidade.IEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@XmlRootElement(name = "autor")
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
	
	@OneToMany(mappedBy = "autor", orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonManagedReference
	@XmlElementWrapper
	private List<FraseEntidade> frases = new LinkedList<FraseEntidade>();

	
	public AutorEntidade() {
		super();
	}   
	
	public AutorEntidade(Long id, String nome, List<FraseEntidade> frases) {
		super();
		this.id = id;
		this.nome = nome;
		this.frases = frases;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<FraseEntidade> getFrases() {
		return frases;
	}

	public void setFrases(List<FraseEntidade> frases) {
		this.frases = frases;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((frases == null) ? 0 : frases.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		AutorEntidade other = (AutorEntidade) obj;
		if (frases == null) {
			if (other.frases != null)
				return false;
		} else if (!frases.equals(other.frases))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AutorEntidade [id=" + id + ", nome=" + nome + ", frases="
				+ frases + "]";
	}

}