package apiembraer.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Sample")
public class SampleEntity {

	@ManyToOne
	@JoinColumn(name = "Id_Chassi", referencedColumnName = "Id_Chassi")
	private ChassiEntity idChassi;
	
	@ManyToOne
	@JoinColumn(name = "Id_Boletim", referencedColumnName = "Id_Boletim")
	private BoletimEntity IdBoletim;
	
	@Id
	@Column(name = "Status_Sample")
	private String StatusSample;
		
	
}
