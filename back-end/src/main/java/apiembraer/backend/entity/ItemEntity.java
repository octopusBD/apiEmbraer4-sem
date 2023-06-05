package apiembraer.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "ITEM")
public class ItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item")
    @SequenceGenerator(name = "seq_item", sequenceName = "SEQ_ITEM", allocationSize = 1)
	@Column(name = "ID_ITEM")
	private Integer idItem;
	
	@Column(name = "ITEM_NOME")
	private String itemNome;
	
}
