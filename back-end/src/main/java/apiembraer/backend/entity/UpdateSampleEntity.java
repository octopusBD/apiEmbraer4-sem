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
@Table(name = "view_update_sample")
public class UpdateSampleEntity {

	@Id
	@Column(name = "ID_UPDATE_SAMPLE")
	private Integer idUpdateSample;
	
	@Column(name = "ID_USUARIO")
	private Integer idUsuario;
	
	@Column(name = "NOME_USUARIO")
	private String nomeUsuario;

	@Column(name = "ID_CHASSI")
	private Integer idChassi;

	@Column(name = "ID_BOLETIM")
	private Integer idBoletim;

	@Column(name = "BOLETIM")
	private String boletim;

	@Column(name = "MENSAGEM")
	private String mensagem;

	@Column(name = "ID_USUARIO_ALTERANTE")
	private Integer idUsuarioAlterante;

	@Column(name = "NOME_USUARIO_ALTERANTE")
	private String nomeUsuarioAlterante;

}
