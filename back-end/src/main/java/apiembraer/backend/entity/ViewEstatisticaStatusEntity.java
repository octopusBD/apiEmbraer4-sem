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
@Table(name = "view_estatistica_status")
public class ViewEstatisticaStatusEntity {
	
	@Id
	@Column(name = "ID_ESTATISTICA")
	private Integer idEstatistica;
	
	@Column(name = "ID_USUARIO")
	private Integer idUsuario;
	
	@Column(name = "ID_CHASSI")
	private Integer idChassi;

	@Column(name = "NOME_USUARIO")
	private String nomeUsuario;

	@Column(name = "CHASSI")
	private String chassi;

	@Column(name = "STATUS_SAMPLE")
	private String status;
	
	@Column(name = "ITEM")
	private String item;


}
