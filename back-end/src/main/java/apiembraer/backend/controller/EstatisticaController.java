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
import apiembraer.backend.entity.ViewEstatisticaQtdBoletimEntity;
import apiembraer.backend.entity.ViewEstatisticaQtdContidoEntity;
import apiembraer.backend.entity.ViewEstatisticaStatusEntity;
import apiembraer.backend.service.EstatisticaService;

@RestController
@CrossOrigin
@RequestMapping(value = "/estatistica")
public class EstatisticaController {
	
    @Autowired
    private EstatisticaService estatisticaservice;
	
	// VIEW_ESTATISTICA_STATUS //
  	
		// LISTAR //
	    @GetMapping("/listar/status")
	    public ResponseEntity<List<ViewEstatisticaStatusEntity>> getAllStatus() {
	        List<ViewEstatisticaStatusEntity> samples = estatisticaservice.getAllStatus();
	        return new ResponseEntity<>(samples, HttpStatus.OK);
	    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// VIEW_ESTATISTICA_QTD_BOLETIM //
		
		// LISTAR QTD BOLETIM //
	    @GetMapping("/listar/boletim")
	    public ResponseEntity<List<ViewEstatisticaQtdBoletimEntity>> getAllQtdBoletim() {
	    	List<ViewEstatisticaQtdBoletimEntity> samples = estatisticaservice.getAllQtdBoletim();
	    	return new ResponseEntity<>(samples, HttpStatus.OK);
	    }
	    
	    // LISTAR QTD BOLETIM POR ID CHASSI //
	 	@GetMapping("/listar/boletim/{idChassi}")
	 	@ResponseBody
	 	public List<ViewEstatisticaQtdBoletimEntity> getViewSampleEntitiesBol(@PathVariable Integer idChassi) {
	 		List<ViewEstatisticaQtdBoletimEntity> result = estatisticaservice.findByIdChassiBol(idChassi);
	 		return result;
	 	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
	 	
	// VIEW_ESTATISTICA_QTD_CONTIDO //
		 	
	    // LISTAR QTD CONTIDO //
	    @GetMapping("/listar/contido")
	    public ResponseEntity<List<ViewEstatisticaQtdContidoEntity>> getAllQtdContido() {
	    	List<ViewEstatisticaQtdContidoEntity> samples = estatisticaservice.getAllQtdContido();
	    	return new ResponseEntity<>(samples, HttpStatus.OK);
	    }
		
	    // LISTAR QTD CONTIDO POR ID CHASSI //
	  	@GetMapping("/listar/contido/{idChassi}")
	  	@ResponseBody
	  	public List<ViewEstatisticaQtdContidoEntity> getViewSampleEntitiesCon(@PathVariable Integer idChassi) {
	  		List<ViewEstatisticaQtdContidoEntity> result = estatisticaservice.findByIdChassiCon(idChassi);
	  		return result;
	  	}
  	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  	
	  	
	// VIEW_ESTATISTICA_DISPONIBILIDADE //
	    
			// LISTAR DISPONIBILIDADE //
		    @GetMapping("/listar/disponibilidade")
		    public ResponseEntity<List<ViewEstatisticaDisponibilidadeEntity>> getAllDisponibilidade() {
		        List<ViewEstatisticaDisponibilidadeEntity> samples = estatisticaservice.getAllDisponibilidade();
		        return new ResponseEntity<>(samples, HttpStatus.OK);
		    }
		    
			// LISTAR DISPONIBILIDADE POR ID //
			@GetMapping("/listar/disponibilidade/{idUsuario}")
			@ResponseBody
			public List<ViewEstatisticaDisponibilidadeEntity> getViewSampleEntitiesDisp(@PathVariable Integer idUsuario) {
				List<ViewEstatisticaDisponibilidadeEntity> result = estatisticaservice.findByIdUsuario(idUsuario);
				return result;
			}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  	

	
}
