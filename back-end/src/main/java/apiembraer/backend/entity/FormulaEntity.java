package apiembraer.backend.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Entity
@Data
@Table(name = "FORMULA")
public class FormulaEntity {

	@Id
	@Column(name = "ID_FORMULA")
	private Integer idFormula;

	@Column(name = "FORMULA")
	private String formula;

	@ManyToOne
	@JoinColumn(name = "ID_ITEM", referencedColumnName = "ID_ITEM")
	private ItemEntity item;

}
