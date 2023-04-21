package apiembraer.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Data;

@Entity
@Immutable
@Data
@Table(name = "view_listar_usuarios")
public class ViewListarUsuario {

	@Id
	@Column(name = "ID_USUARIO")
	private Integer idUsuario;
	
	@Column(name = "NOME_USUARIO")
	private String nomeUsuario;
	
	@Column(name = "LOGIN_USUARIO")
	private String loginUsuario;
	
	@Column(name = "SENHA_USUARIO")
	private String senhaUsuario;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "ID_PERMISSAO")
	private Integer idPermissao;

	@Column(name = "PERMISSAO")
	private String permissao;
	
	
}
