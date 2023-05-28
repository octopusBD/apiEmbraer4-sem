package apiembraer.backend.entity;

import java.sql.Timestamp;

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
@Table(name = "FORMULA")
public class FormulaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_formula")
    @SequenceGenerator(name = "seq_formula", sequenceName = "SEQ_FORMULA", allocationSize = 1)
	@Column(name = "ID_FORMULA")
	private Integer idFormula;

	@Column(name = "FORMULA")
	private String formula;
	
	@Column(name = "DT_CADASTRO")             
	private Timestamp dtCadastro;
	
	
	@Column(name = "ID_ITEM")
	private Integer item;

}
