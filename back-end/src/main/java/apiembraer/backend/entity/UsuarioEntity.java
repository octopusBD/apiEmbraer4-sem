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
@Table(name = "Usuario")
public class UsuarioEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usario")
    @SequenceGenerator(name = "seq_usario", sequenceName = "SEQ_BOLETIM", allocationSize = 1)
	@Column(name = "Id_Usuario")
	private Integer IdUsuario;
	
	@Column(name = "Nome_Usuario")
	private String nomeUsuario;

	@Column(name = "Senha_Usuario")
	private String senhaUsuario;
	
	@ManyToOne
	@JoinColumn(name = "Id_Permissao", referencedColumnName = "Id_Permissao")
	private PermissaoEntity IdPermissao;
}
