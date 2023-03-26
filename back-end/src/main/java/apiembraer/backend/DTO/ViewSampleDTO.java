package apiembraer.backend.DTO;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ViewSampleDTO {

	private Integer idChassi;
	private Integer idBoletim;
	private String nomeUsuario;
	private String boletim;
	private String item;
	private String statusSample;
	private String chassi;
	private String ultUsuAlt;
	private Timestamp dtUltEdicao;
	
}
