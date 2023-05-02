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
@Table(name = "VIEW_ITEM_CHASSI")
public class ViewItemChassiEntity {
	
	@Id
	@Column(name = "ITEM")
	private String item;
	
	@Column(name = "QUANTIDADE")
	private Integer quantidade;
}
