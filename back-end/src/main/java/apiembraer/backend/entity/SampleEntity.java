package apiembraer.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Sample")
public class SampleEntity {

	@OneToMany
	@JoinColumn(name = "Chassi", referencedColumnName = "Id_Chassi")
	private Integer idChassi;
	
	@OneToMany
	@JoinColumn(name = "Boletim", referencedColumnName = "Id_Boletim")
	private Integer idBoletim;
	
	@Id
	@Column(name = "Status")
	private String Status;
}
