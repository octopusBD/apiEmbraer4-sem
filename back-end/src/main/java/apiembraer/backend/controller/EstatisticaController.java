package apiembraer.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import apiembraer.backend.entity.ViewEstatisticaDisponibilidadeEntity;
import apiembraer.backend.service.EstatisticaService;

@RestController
@CrossOrigin
@RequestMapping(value = "/estatistica")
public class EstatisticaController {
	
    @Autowired
    private EstatisticaService estatisticaservice;
	
	// LISTAR //
    @GetMapping("/listar/disponibilidade")
    public ResponseEntity<List<ViewEstatisticaDisponibilidadeEntity>> getAll() {
        List<ViewEstatisticaDisponibilidadeEntity> samples = estatisticaservice.getAll();
        return new ResponseEntity<>(samples, HttpStatus.OK);
    }
    
	// LISTAR POR ID //
	@GetMapping("/listar/disponibilidade/{idUsuario}")
	@ResponseBody
	public List<ViewEstatisticaDisponibilidadeEntity> getViewSampleEntities(@PathVariable Integer idUsuario) {
		List<ViewEstatisticaDisponibilidadeEntity> result = estatisticaservice.findByIdUsuario(idUsuario);
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
