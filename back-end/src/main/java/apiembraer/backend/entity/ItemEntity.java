package apiembraer.backend.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Entity
@Data
@Table(name = "ITEM")
public class ItemEntity {

	@Id
	@Column(name = "ID_ITEM")
	private Integer idItem;
	
	@Column(name = "ITEM_NOME")
	private String itemNome;
	
}
