package apiembraer.backend.security;

import lombok.Data;

@Data
public class Login {

    private String loginUsuario;
    
    private String senhaUsuario;
  
    private String autorizacao;
  
    private String token;

    
}
