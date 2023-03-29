package apiembraer.backend.entity;

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
@Table(name = "UPDATE_SAMPLE")
public class UpdateSampleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_update_sample")
    @SequenceGenerator(name = "seq_update_sample", sequenceName = "SEQ_UPDATE_SAMPLE", allocationSize = 1)
	@Column(name = "ID_UPDATE_SAMPLE")
	private Integer idUpdateSample;
	
	@Column(name = "ID_CHASSI")
	private Integer idChassi;
	
	@Column(name = "ID_BOLETIM")
	private Integer idBoletim;
	
	@Column(name = "BOLETIM")
	private String boletim;
	
	@Column(name = "MENSAGEM")
	private String mensagem;
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO_ALTERANTE", referencedColumnName = "ID_USUARIO")
	private UsuarioEntity idUsuarioAlterante;
	
	@ManyToOne
	@JoinColumn(name = "NOME_USUARIO_ALTERANTE", referencedColumnName = "NOME_USUARIO")
	private UsuarioEntity nomeUsuarioAlterante;
		
}
