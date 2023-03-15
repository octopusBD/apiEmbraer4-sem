package apiembraer.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Boletim")
public class BoletimEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BOLETIM")
	@Column(name = "Id_Boletim")
	private Integer idBoletim;
	
	@Column(name = "Boletim")
	private Integer Boletim;
	
	@Column(name = "Itens")
	private Integer itens;



}
