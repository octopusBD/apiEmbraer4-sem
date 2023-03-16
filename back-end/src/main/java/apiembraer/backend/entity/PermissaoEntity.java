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
@Table(name = "Permissao")
public class PermissaoEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PERMISSAO")
	@Column(name = "Id_Permissao")
	private Integer IdPermissao;
	
	@Column(name = "Nome_Permissao")
	private String nomePermissao;

}
