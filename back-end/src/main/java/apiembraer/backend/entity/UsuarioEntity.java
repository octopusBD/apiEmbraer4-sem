package apiembraer.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "USUARIO_SPRINT2")
public class UsuarioEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usario")
    @SequenceGenerator(name = "seq_usario", sequenceName = "SEQ_BOLETIM", allocationSize = 1)
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

	@ManyToOne
	@JoinColumn(name = "ID_PERMISSAO", referencedColumnName = "ID_PERMISSAO")
	private PermissaoEntity idPermissao;

}
