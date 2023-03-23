package apiembraer.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "CADASTRO")
public class CadastroEntity {
	@Id
	@Column(name = "ID_CADASTRO")
	private Integer idCadastro;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
	private UsuarioEntity idUsuario;

	@ManyToOne
	@JoinColumn(name = "ID_CHASSI", referencedColumnName = "ID_CHASSI")
	private ChassiEntity idChassi;
}
