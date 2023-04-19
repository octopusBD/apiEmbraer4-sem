package apiembraer.backend.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import apiembraer.backend.entity.UsuarioEntity;
import apiembraer.backend.entity.PermissaoEntity;
import apiembraer.backend.repository.UsuarioRepository;

import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class SegurancaService implements UserDetailsService{


    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	UsuarioEntity usuario = usuarioRepo.findByNomeUsuario(username);
        if(usuario == null) {
            throw new UsernameNotFoundException("Não achei!");
        }
        return User.builder().username(username).password(usuario.getSenhaUsuario())
            .authorities(usuario.getIdPermissao().getPermissao())
            .build();
    }
    
}
