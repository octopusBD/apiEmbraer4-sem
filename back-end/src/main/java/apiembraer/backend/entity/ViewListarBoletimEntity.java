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
@Table(name = "VIEW_LISTAR_BOLETIM")
public class ViewListarBoletimEntity {

	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "BOLETIM")
	private String boletim;
	
}
