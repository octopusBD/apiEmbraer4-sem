package apiembraer.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apiembraer.backend.DTO.UsuarioDTO;
import apiembraer.backend.entity.PermissaoEntity;
import apiembraer.backend.entity.SampleEntity;
import apiembraer.backend.entity.UsuarioEntity;
import apiembraer.backend.repository.PermissaoRepository;
import apiembraer.backend.repository.UsuarioRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PermissaoRepository permissaoRepository;
	
    @Autowired
    private PasswordEncoder encoder;

	    //@PreAuthorize("hasRole('ADMIN')")
		public UsuarioEntity atualizarUsuario(Integer idUsuario, UsuarioDTO usuarioNovo) {
		    Optional<UsuarioEntity> optionalUsuario = usuarioRepository.findById(idUsuario);
		    if (optionalUsuario.isPresent()) {
		        UsuarioEntity usuario = optionalUsuario.get();
		        usuario.setNomeUsuario(usuarioNovo.getNomeUsuario());
		        usuario.setLoginUsuario(usuarioNovo.getLoginUsuario());
		        usuario.setSenhaUsuario(usuarioNovo.getSenhaUsuario());
		        usuario.setEmail(usuarioNovo.getEmail());
	
		        Optional<PermissaoEntity> optionalPermissao = permissaoRepository.findByIdPermissao(usuarioNovo.getIdPermissao().getIdPermissao());
		        if (optionalPermissao.isPresent()) {
		            usuario.setIdPermissao(optionalPermissao.get());
		        }
	
		        return usuarioRepository.save(usuario);
		    } else {
		        return null;
		    }
		}


	   public void deleteById(Integer idUsuario) {
		   usuarioRepository.deleteById(idUsuario);
	    }
	   
	    // LISTAR //
	    public List<UsuarioEntity> getAllUsuarios() {
	        return usuarioRepository.findAll();
	    }
	   
	}

