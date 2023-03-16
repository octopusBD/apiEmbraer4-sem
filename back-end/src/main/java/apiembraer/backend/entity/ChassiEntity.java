package apiembraer.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Chassi")
public class ChassiEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_chassi")
    @SequenceGenerator(name = "seq_chassi", sequenceName = "SEQ_CHASSI", allocationSize = 1)
	@Column(name = "Id_Chassi")
	private Integer idChassi;
	
	@Column(name = "Chassi")
	private String chassi;
}
