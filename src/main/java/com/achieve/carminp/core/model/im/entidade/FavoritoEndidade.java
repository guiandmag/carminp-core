package com.achieve.carminp.core.model.im.entidade;

import java.lang.Long;
import java.util.LinkedList;
import java.util.List;

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
 * Entidade para representar o usuario a ser salvo no DB.
 *
 * @author guilherme.magalhaes
 * @version 2.0
 * @see IEntity
 */
@Entity
@Table(name="TBL_FAVORITO")
@SequenceGenerator(name = FavoritoEndidade.FAVORITO_SEQUENCIA, sequenceName = FavoritoEndidade.FAVORITO_SEQUENCIA, initialValue = 1, allocationSize = 50)
@XmlRootElement(name = "favorito")
@XmlAccessorType(XmlAccessType.FIELD)
public class FavoritoEndidade implements IEntity<Long> {
	
	private static final long serialVersionUID = 1L;
	
	@Transient
	public static final String FAVORITO_SEQUENCIA = "FAVORITO_SEQUENCIA";

	@Id
	@GeneratedValue(generator = FAVORITO_SEQUENCIA, strategy = GenerationType.SEQUENCE)
	@Column(name = "favorito_id")
	@XmlAttribute
	private Long id;
	
	@Column(name = "favorito_favoritado", nullable = false)
	@XmlElement
	private boolean favoritado;
	
	@OneToMany(mappedBy = "favorito")
	@XmlElementWrapper
	private List<FraseEntidade> frases = new LinkedList<>();
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id", nullable = false)
	@Valid
	@XmlElement
	private UsuarioEntidade usuario;

	public FavoritoEndidade() {
		super();
	}   
	
	public FavoritoEndidade(Long id, boolean favoritado,
			List<FraseEntidade> frases, UsuarioEntidade usuario) {
		super();
		this.id = id;
		this.favoritado = favoritado;
		this.frases = frases;
		this.usuario = usuario;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public Long getId() {
		return this.id;
	}

	public boolean isFavoritado() {
		return favoritado;
	}

	public void setFavoritado(boolean favoritado) {
		this.favoritado = favoritado;
	}

	public List<FraseEntidade> getFrases() {
		return frases;
	}

	public void setFrases(List<FraseEntidade> frases) {
		this.frases = frases;
	}

	public UsuarioEntidade getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntidade usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (favoritado ? 1231 : 1237);
		result = prime * result + ((frases == null) ? 0 : frases.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		FavoritoEndidade other = (FavoritoEndidade) obj;
		if (favoritado != other.favoritado)
			return false;
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
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FavoritoEndidade [id=" + id + ", favoritado=" + favoritado
				+ ", frases=" + frases + ", usuario=" + usuario + "]";
	}

}