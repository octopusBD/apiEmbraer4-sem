package apiembraer.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import lombok.Data;

@Entity
@Immutable
@Data
@Table(name = "VIEW_PERMISSAO_USUARIO")
public class ViewPermissaoUsuarioEntity {

    @Id
    @Column(name = "ADMINISTRADOR")
    private Integer administrador;

    @Column(name = "EDITOR")
    private Integer editor;

    @Column(name = "CONSULTOR")
    private Integer consultor;

}
