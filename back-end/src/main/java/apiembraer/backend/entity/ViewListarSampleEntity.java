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
@Table(name = "VIEW_LISTAR_SAMPLE")
public class ViewListarSampleEntity {
	
	@Id
	@Column(name = "ID_SAMPLE")
	private Integer IdSample;
	
	@Column(name = "ID_CHASSI")
	private Integer IdChassi;
	
	@Column(name = "ID_BOLETIM")
	private Integer IdBoletim;
	
	@Column(name = "BOLETIM")
	private String Boletim;
	

}
