package apiembraer.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apiembraer.backend.entity.FormulaEntity;
import apiembraer.backend.repository.FormulaRepository;

@Service
public class FormulaService {

	@Autowired
    private FormulaRepository formulaRepository;
	
	public List<FormulaEntity> getAllFormula() {
        return formulaRepository.findAll();
    }

	
}
