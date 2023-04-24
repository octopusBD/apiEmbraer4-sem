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
@Table(name = "view_status_chassi")
public class ViewStatusChassiEntity {

	@Id
	@Column(name = "CHASSI")
	private String chassi;
	
	@Column(name = "INCORPORATED")
	private Integer incorporated;
	
	@Column(name = "NOT_INCORPORATED")
	private Integer notIncorporated;

	@Column(name = "NOT_APPLICABLE")
	private Integer notApplicable;
	
}
