package apiembraer.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Data;

@Entity
@Immutable
@Data
@Table(name = "VIEW_CADASTRO")
public class ViewCadastroEntity {

	@Id
	@Column(name = "ID_CADASTRO")
	private Integer idCadastro;
	
	
	@Column(name = "ID_USUARIO")
	private Integer idUsuario;
	
	@Column(name = "ID_CHASSI")
	private Integer idChassi;
	
	@Column(name = "ID_PERMISSAO")
	private Integer idPermissao;
	
}
