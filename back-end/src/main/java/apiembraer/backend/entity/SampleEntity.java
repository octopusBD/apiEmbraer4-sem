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
@Table(name = "SAMPLE")
public class SampleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sample")
    @SequenceGenerator(name = "seq_sample", sequenceName = "SEQ_SAMPLE", allocationSize = 1)
	@Column(name = "ID_SAMPLE")
	private Integer idSample;
	
	@ManyToOne
	@JoinColumn(name = "ID_CHASSI", referencedColumnName = "ID_CHASSI")
	private ChassiEntity Chassi;

	@ManyToOne
	@JoinColumn(name = "ID_BOLETIM", referencedColumnName = "ID_BOLETIM")
	private BoletimEntity Boletim;

	@Column(name = "STATUS_SAMPLE")
	private String statusSample;

	@Column(name = "ULT_USU_ALT")
	private String ultUsuAlt;

	@Column(name = "DT_ULT_EDICAO")
	private Timestamp dtUltEdicao;

}
