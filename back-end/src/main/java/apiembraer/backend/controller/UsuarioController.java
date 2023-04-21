package apiembraer.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apiembraer.backend.DTO.UsuarioDTO;
import apiembraer.backend.entity.UsuarioEntity;
import apiembraer.backend.entity.ViewListarUsuario;
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
	  
	    // LISTAR //
	    @GetMapping("/listar")
	    public ResponseEntity<List<ViewListarUsuario>> getAllUsuarios() {
	        List<ViewListarUsuario> usuarios = usuarioService.getAllUsuarios();
	        return new ResponseEntity<>(usuarios, HttpStatus.OK);
	    }

	    
	}
	

