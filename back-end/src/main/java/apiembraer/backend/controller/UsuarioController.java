package apiembraer.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import apiembraer.backend.DTO.UsuarioDTO;
import apiembraer.backend.entity.PermissaoEntity;
import apiembraer.backend.entity.UsuarioEntity;
import apiembraer.backend.service.UsuarioService;

@RestController
@CrossOrigin
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PutMapping("/update/{idUsuario}")
	public ResponseEntity<UsuarioEntity> atualizarUsuario(@PathVariable Integer idUsuario, @RequestBody UsuarioDTO usuarioNovo) {
	    UsuarioEntity usuarioAtualizado = usuarioService.atualizarUsuario(idUsuario, usuarioNovo);
	    if (usuarioAtualizado != null) {
	        return ResponseEntity.ok(usuarioAtualizado);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	  @DeleteMapping("/delete/{idUsuario}")
	    public void deleteById(@PathVariable Integer idUsuario) {
		  usuarioService.deleteById(idUsuario);
	    }
	}
	

