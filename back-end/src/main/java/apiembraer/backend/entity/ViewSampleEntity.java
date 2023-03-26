package apiembraer.backend.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Data;

@Entity
@Immutable
@Data
@Table(name = "view_sample")
public class ViewSampleEntity {
	
	@Column(name = "ID_USUARIO")
	private Integer idUsuario;
	
	@Id
	@Column(name = "ID_CHASSI")
	private Integer idChassi;

	@Column(name = "ID_BOLETIM")
	private Integer idBoletim;

	@Column(name = "NOME_USUARIO")
	private String nomeUsuario;
	
	@Column(name = "BOLETIM")
	private String boletim;
	
	@Column(name = "ITEM")
	private String item;
	
	@Column(name = "STATUS_SAMPLE")
	private String statusSample;
	
	@Column(name = "CHASSI")
	private String chassi;

	@Column(name = "ULT_USU_ALT")
	private String ultUsuAlt;

	@Column(name = "DT_ULT_EDICAO")
	private Timestamp dtUltEdicao;
	
	@Column(name = "ULTIMO_USUARIO_ALTERADO")
	private String ultimoUsuarioAlterado;

}