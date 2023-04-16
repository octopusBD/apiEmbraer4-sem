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
@Table(name = "view_estatistica_disponibilidade")
public class ViewEstatisticaDisponibilidadeEntity {
	
	@Id
	@Column(name = "ID")
	private Integer idDisponibilidade;
	
	@Column(name = "CHASSI")
	private String chassi;
	
	@Column(name = "ID_CHASSI")
	private Integer idChassi;

	@Column(name = "ID_USUARIO")
	private Integer idUsuario;

	@Column(name = "ITEM")
	private String item;

	@Column(name = "STATUS")
	private String status;

}
