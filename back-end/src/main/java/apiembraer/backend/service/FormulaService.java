package apiembraer.backend.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apiembraer.backend.entity.FormulaEntity;
import apiembraer.backend.entity.ViewEstatisticaDisponibilidadeEntity;
import apiembraer.backend.entity.ViewListarLogicaEntity;
import apiembraer.backend.repository.FormulaRepository;
import apiembraer.backend.repository.ViewListarLogicaRepository;

@Service
public class FormulaService {

	@Autowired
    private FormulaRepository formulaRepository;
	
	@Autowired
    private ViewListarLogicaRepository listarLogicaRepository;
	
	public List<FormulaEntity> getAllFormula() {
        return formulaRepository.findAll();
    }

	public FormulaEntity saveFormula(FormulaEntity formula) {
        return formulaRepository.save(formula);
    }
	
	public void deleteById(Integer idFormula) {
		formulaRepository.deleteById(idFormula);
	}
	
	public FormulaEntity atualizarFormula(Integer idFormula, String formula, Integer item) {
        Optional<FormulaEntity> formulaExistenteOptional = formulaRepository.findById(idFormula);
        System.err.println("OI");
        System.err.println(formulaExistenteOptional);
        if (formulaExistenteOptional.isPresent()) {
            FormulaEntity formulaExistente = formulaExistenteOptional.get();
            formulaExistente.setFormula(formula);
            formulaExistente.setItem(item);
            formulaExistente.setDtCadastro(new Timestamp(System.currentTimeMillis()));
            return formulaRepository.save(formulaExistente);
        }

        return null; 
    }
	
	public List<ViewListarLogicaEntity> getAllListarLogica() {
        return listarLogicaRepository.findAll();
    }

	public List<ViewListarLogicaEntity> findByIdUsuario(Integer idUsuario) {
		return listarLogicaRepository.findByIdUsuario(idUsuario);
	}
	
}
