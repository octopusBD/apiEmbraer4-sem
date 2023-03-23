package apiembraer.backend.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import oracle.security.o3logon.a;

@Entity
@Data
@Table(name = "SAMPLE")
@IdClass(SampleEntityPk.class)
public class SampleEntity {
	@Id
	@ManyToOne
	@JoinColumn(name = "ID_CHASSI", referencedColumnName = "ID_CHASSI")
	private ChassiEntity idChassi;

	@Id
	@ManyToOne
	@JoinColumn(name = "ID_BOLETIM", referencedColumnName = "ID_BOLETIM")
	private BoletimEntity idBoletim;

	@Column(name = "STATUS_SAMPLE")
	private String statusSample;

	@Column(name = "ULT_USU_ALT")
	private String ultUsuAlt;

	@Column(name = "DT_ULT_EDICAO")
	private LocalDateTime dtUltEdicao;
}
