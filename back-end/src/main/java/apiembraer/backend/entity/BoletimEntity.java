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
@Table(name = "BOLETIM")
public class BoletimEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_boletim")
    @SequenceGenerator(name = "seq_boletim", sequenceName = "SEQ_BOLETIM", allocationSize = 1)
	@Column(name = "ID_BOLETIM")
	private Integer idBoletim;

	@Column(name = "BOLETIM")
	private String boletim;

	@Column(name = "ITEM")
	private String item;

}
