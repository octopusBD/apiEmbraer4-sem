package apiembraer.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apiembraer.backend.entity.FormulaEntity;
import apiembraer.backend.service.FormulaService;

@RestController
@CrossOrigin
@RequestMapping(value = "/formula")
public class FormulaController {
	
	@Autowired
	private FormulaService formulaservice;
	
	@GetMapping("/listar")
	public ResponseEntity<List<FormulaEntity>> getAllFormula() {
		List<FormulaEntity> samples = formulaservice.getAllFormula();
		return new ResponseEntity<>(samples, HttpStatus.OK);
	}
	
	@PostMapping("/save")
    public ResponseEntity<FormulaEntity> saveFormula(@RequestBody FormulaEntity formula) {
		FormulaEntity savedFormula = formulaservice.saveFormula(formula);
        return new ResponseEntity<>(savedFormula, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/delete/{idFormula}")
    public void deleteById(@PathVariable Integer idFormula) {
    	formulaservice.deleteById(idFormula);
    }
	
}
