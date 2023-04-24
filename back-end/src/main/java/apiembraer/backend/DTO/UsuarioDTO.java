package apiembraer.backend.DTO;

import apiembraer.backend.entity.PermissaoEntity;
import lombok.Data;

@Data
public class UsuarioDTO {

	private String loginUsuario;
	private String permissao;
	private String idUsuario;

}
