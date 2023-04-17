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
@Table(name = "view_estatistica_qtd_contido")
public class ViewEstatisticaQtdContidoEntity {

	@Id
	@Column(name = "ID_QTD_CONTIDO")
	private Integer idQtdContido;
	
	@Column(name = "ID_CHASSI")
	private Integer idChassi;
	
	@Column(name = "CHASSI")
	private String chassi;
	
	@Column(name = "STATUS")
	private String status;

	@Column(name = "QTD_CONTIDO")
	private Integer qtdBoletins;
	
}
