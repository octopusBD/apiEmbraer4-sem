package apiembraer.backend.entity;

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
@Table(name = "LOGICA")
public class LogicaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_logica")
    @SequenceGenerator(name = "seq_logica", sequenceName = "SEQ_LOGICA", allocationSize = 1)
	@Column(name = "ID_LOGICA")
	private Integer idFormula;
	
	@ManyToOne
	@JoinColumn(name = "ID_CHASSI", referencedColumnName = "ID_CHASSI")
	private ChassiEntity idChassi;
	
	@ManyToOne
	@JoinColumn(name = "ID_ITEM", referencedColumnName = "ID_ITEM")
	private ItemEntity item;

}
