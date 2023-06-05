package apiembraer.backend.entity;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Data;

@Entity
@Immutable
@Data
@Table(name = "view_listar_formula")
public class ViewListarFormulaEntity {

	@Id
	@Column(name = "ID_FORMULA")
	private Integer idFormula;
	
	@Column(name = "FORMULA")
	private String formula;
	
	@Column(name = "ID_ITEM")
	private Integer idItem;
	
	@Column(name = "ITEM_NOME")
	private String itemNome;
	
	@Column(name = "DT_CADASTRO")
	private Timestamp dtCadastro;
	
	
}





