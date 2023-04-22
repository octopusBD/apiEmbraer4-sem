package apiembraer.backend.DTO;

import apiembraer.backend.entity.PermissaoEntity;
import lombok.Data;

@Data
public class UsuarioDTO {

	private Integer idUsuario;
	private String nomeUsuario;
	private String loginUsuario;
	private String senhaUsuario;
	private String email;
	private PermissaoEntity idPermissao;

}
