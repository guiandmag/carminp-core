package com.achieve.carminp.core.model.im.entidade;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.achieve.carminp.core.model.in.entidade.IEntity;

/**
 * Entidade para representar o usuario a ser salvo no DB.
 *
 * @author guilherme.magalhaes
 * @version 1.0
 * @see IEntity
 */
@Entity
@Table(name = "TBL_USUARIO")
@SequenceGenerator(name = UsuarioEntidade.USUARIO_SEQUENCIA, sequenceName = UsuarioEntidade.USUARIO_SEQUENCIA, initialValue = 1, allocationSize = 50)
@XmlRootElement(name = "usuario")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsuarioEntidade implements IEntity<Long> {

	@Transient
	public static final String USUARIO_SEQUENCIA = "USUARIO_SEQUENCIA";
	
	@Id
	@GeneratedValue(generator = USUARIO_SEQUENCIA, strategy = GenerationType.SEQUENCE)
	@Column(name = "usuario_id", updatable = false, unique = true, nullable = false)
	@XmlAttribute
	private Long id;
	
	@Column(name = "usuario_nome", nullable = false, length = 150)
	@XmlElement(name = "nome")
	private String nomeUsuario;
	
	@Column(name = "usuario_email", nullable = false, length = 100)
	@XmlElement
	private String email;
	
	@Column(name = "usuario_senha", nullable = false, length = 50)
	@XmlElement
	private String senha;
	
	private static final long serialVersionUID = 1L;

	public UsuarioEntidade() {
		super();
	}

	public UsuarioEntidade(Long id, String nomeUsuario, String email,
			String senha) {
		super();
		this.id = id;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.senha = senha;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public Long getId() {
		return id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((nomeUsuario == null) ? 0 : nomeUsuario.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		UsuarioEntidade other = (UsuarioEntidade) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeUsuario == null) {
			if (other.nomeUsuario != null)
				return false;
		} else if (!nomeUsuario.equals(other.nomeUsuario))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsuarioEntidade [id=" + id + ", nomeUsuario=" + nomeUsuario
				+ ", email=" + email + ", senha=" + senha + "]";
	}
   
}