package apiembraer.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import apiembraer.backend.security.JwtUtils;
import apiembraer.backend.security.Login;


@RestController
@CrossOrigin
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping()
    public Login autenticar(@RequestBody Login login) throws JsonProcessingException {
    Authentication auth = new UsernamePasswordAuthenticationToken(login.getLoginUsuario(), login.getSenhaUsuario());
    auth = authManager.authenticate(auth);
    
    login.setSenhaUsuario(null);
    login.setToken(JwtUtils.generateToken(auth));
    login.setAutorizacao(auth.getAuthorities().iterator().next().getAuthority());

    return login;
    }
    

}
