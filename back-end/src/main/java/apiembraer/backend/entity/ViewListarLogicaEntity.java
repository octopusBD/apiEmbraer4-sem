package apiembraer.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "VIEW_LISTAR_LOGICA")
public class ViewListarLogicaEntity {

	@Id
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "ID_CHASSI")
	private Integer idChassi;
	
	@Column(name = "ID_ITEM")
	private Integer idItem;
	
	@Column(name = "ITEM_NOME")
	private String itemNome;
	
	@Column(name = "CHASSI")
	private String chassi;
	
	@Column(name = "ID_USUARIO")
	private Integer idUsuario;
	
	@Column(name = "NOME_USUARIO")
	private String nomeUsuario;
	
	@Column(name = "STATUS_SAMPLE")
	private char statusSample;
	
	
}
