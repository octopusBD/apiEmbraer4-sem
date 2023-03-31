package apiembraer.backend.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "SAMPLE")
public class SampleEntity {

	@ManyToOne
	@JoinColumn(name = "ID_CHASSI", referencedColumnName = "ID_CHASSI")
	private ChassiEntity idChassi;

	@ManyToOne
	@JoinColumn(name = "ID_BOLETIM", referencedColumnName = "ID_BOLETIM")
	private BoletimEntity idBoletim;

	@Id
	@Column(name = "STATUS_SAMPLE")
	private String statusSample;

	@Column(name = "ULT_USU_ALT")
	private String ultUsuAlt;

	@Column(name = "DT_ULT_EDICAO")
	private Timestamp dtUltEdicao;

}
