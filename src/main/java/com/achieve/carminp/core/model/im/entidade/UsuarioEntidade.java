package com.achieve.carminp.core.model.im.entidade;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

import com.achieve.carminp.core.model.in.entidade.IEntity;

/**
 * Entidade para representar o usuario a ser salvo no DB.
 *
 * @author guilherme.magalhaes
 * @since 11/2014
 * @version 2.0
 * @see IEntity
 */
@Entity
@Table(name = "TBL_USUARIO")
@SequenceGenerator(name = UsuarioEntidade.USUARIO_SEQUENCIA, sequenceName = UsuarioEntidade.USUARIO_SEQUENCIA, initialValue = 1, allocationSize = 50)
@XmlRootElement(name = "usuario")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
	@NamedQuery(name  = "UsuarioEntidade.getOnlyUser",
				query = "SELECT u.id, u.nomeUsuario, u.email, u.senha, u.urlFoto FROM UsuarioEntidade u ORDER BY u.id"),
	@NamedQuery(name  = "UsuarioEntidade.getOnlyUserByName",
				query = "SELECT u.id, u.nomeUsuario, u.email, u.senha, u.urlFoto FROM UsuarioEntidade u WHERE u.nomeUsuario = :nome")	
})
public class UsuarioEntidade implements IEntity<Long> {
	
	private static final long serialVersionUID = 1L;
	
	@Transient
	public static final String USUARIO_SEQUENCIA = "USUARIO_SEQUENCIA";
	
	@Id
	@GeneratedValue(generator = USUARIO_SEQUENCIA, strategy = GenerationType.SEQUENCE)
	@Column(name = "usuario_id")
	@XmlID
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
	
	@Column(name = "usuario_url_foto", length = 250)
	@XmlElement
	private String urlFoto;
	
	@OneToMany(mappedBy = "usuario", orphanRemoval = true)
	@XmlElementWrapper
	private List<FavoritoEntidade> favoritos = new LinkedList<>();
	
	public UsuarioEntidade() {
		super();
	}

	public UsuarioEntidade(Long id, String nomeUsuario, String email,
			String senha, String urlFoto) {
		super();
		this.id = id;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.senha = senha;
		this.urlFoto = urlFoto;
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

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
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
		result = prime * result + ((urlFoto == null) ? 0 : urlFoto.hashCode());
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
		if (urlFoto == null) {
			if (other.urlFoto != null)
				return false;
		} else if (!urlFoto.equals(other.urlFoto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsuarioEntidade [id=" + id + ", nomeUsuario=" + nomeUsuario
				+ ", email=" + email + ", senha=" + senha + ", urlFoto="
				+ urlFoto + "]";
	}
	
}