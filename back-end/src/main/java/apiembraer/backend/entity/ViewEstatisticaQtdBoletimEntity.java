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
@Table(name = "view_estatistica_qtd_boletim")
public class ViewEstatisticaQtdBoletimEntity {

	@Id
	@Column(name = "ID_QTD_BOLETIM")
	private Integer idQtdBoletim;
	
	@Column(name = "ID_CHASSI")
	private Integer idChassi;
	
	@Column(name = "CHASSI")
	private String chassi;

	@Column(name = "QTD_BOLETINS")
	private Integer qtdBoletins;
	
}
