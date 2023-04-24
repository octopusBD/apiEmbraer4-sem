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
@Table(name = "view_estatistica_usuario")
public class ViewEstatisticaUsuario {

	@Id
	@Column(name = "ID")
	private Integer idEstatisticaUsuario;
	
	@Column(name = "ID_PERMISSAO")
	private Integer idPermissao;
	
	@Column(name = "PERMISSAO")
	private String permissao;

	@Column(name = "QTD")
	private Integer qtdPermissao;
}
