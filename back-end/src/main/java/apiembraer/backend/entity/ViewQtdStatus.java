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
@Table(name = "VIEW_QTD_STATUS")
public class ViewQtdStatus {

	@Id
	@Column(name = "ID")
	private Integer idQtdStatus;
	
	@Column(name = "ID_BOLETIM")
	private Integer idBoletim;
	
	@Column(name = "ITEM")
	private String item;

	@Column(name = "INCORPORATED_PERCENTAGE")
	private Integer incorporatedPercentage;
	
	@Column(name = "NOT_INCORPORATED_PERCENTAGE")
	private Integer notIncorporatedPercentage;
	
	@Column(name = "APPLICABLE_PERCENTAGE")
	private Integer applicablePercentage;
	
}
