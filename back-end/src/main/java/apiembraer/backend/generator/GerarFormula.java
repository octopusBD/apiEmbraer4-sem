package apiembraer.backend.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import apiembraer.backend.entity.BoletimEntity;
import apiembraer.backend.entity.ChassiEntity;
import apiembraer.backend.entity.FormulaEntity;
import apiembraer.backend.entity.ItemEntity;
import apiembraer.backend.entity.LogicaEntity;
import apiembraer.backend.entity.SampleEntity;
import apiembraer.backend.entity.ViewListarSampleEntity;
import apiembraer.backend.repository.FormulaRepository;
import apiembraer.backend.repository.LogicaRepository;
import apiembraer.backend.repository.SampleRepository;
import apiembraer.backend.repository.ViewListarSampleRepository;

@Component
public class GerarFormula {

	@Autowired
	private ViewListarSampleRepository sampleRepository;

	@Autowired
	private FormulaRepository formulaRepository;
	
	@Autowired
	private LogicaRepository logicaRepository;

	public GerarFormula(ViewListarSampleRepository sampleRepository, FormulaRepository formulaRepository, LogicaRepository logicaRepository) {
	    this.sampleRepository = sampleRepository;
	    this.formulaRepository = formulaRepository;
	    this.logicaRepository = logicaRepository;
	}


	public static boolean gerarItem(String[] boletins, String formula) {
		// Verifica se a fórmula é uma fórmula simples (sem operadores lógicos)
		if (!formula.contains("and") && !formula.contains("or")) {
			return Arrays.asList(boletins).contains(formula);
		}

		// Verifica se a fórmula contém o operador "and"
		if (formula.contains("and")) {
			String[] partesFormula = formula.split(" and ");
			for (String parte : partesFormula) {
				if (!gerarItem(boletins, parte)) {
					return false;
				}
			}
			return true;
		}

		// Verifica se a fórmula contém o operador "or"
		if (formula.contains("or")) {
			String[] partesFormula = formula.split(" or ");
			for (String parte : partesFormula) {
				if (gerarItem(boletins, parte)) {
					return true;
				}
			}
			return false;
		}

		// Fórmula inválida
		return false;
	}

	public void processarSamples() {
		List<ViewListarSampleEntity> samples = sampleRepository.findAll();
		List<FormulaEntity> formulas = formulaRepository.findAll();

		for (ViewListarSampleEntity sample : samples) {
			Integer IdChassi = sample.getIdChassi();
			boolean formulaValida = false;
			String formulaEncontrada = null;
			Integer idItemEncontrado = null;

			List<String> boletinsIdChassi = new ArrayList<>();

			
	            for (ViewListarSampleEntity sampleBoletim : samples) {
	                if (sampleBoletim.getIdChassi().equals(IdChassi)) {
	                    boletinsIdChassi.add(sampleBoletim.getBoletim());
	                }
	            
	            }

			for (FormulaEntity formula : formulas) {
				boolean resultado = gerarItem(boletinsIdChassi.toArray(new String[0]), formula.getFormula());
				if (resultado) {
					formulaValida = true;
					formulaEncontrada = formula.getFormula();
                    idItemEncontrado = formula.getItem();
					break;
				}
			}

			if (formulaValida) {
			    FormulaEntity formulaEncontradaEntity = formulaRepository.findByFormula(formulaEncontrada);
			    if (formulaEncontradaEntity != null && idItemEncontrado != null && IdChassi != null) {
			        LogicaEntity itemLogica = new LogicaEntity();
			        itemLogica.setItem(idItemEncontrado);
			        itemLogica.setIdChassi(IdChassi);
			        logicaRepository.save(itemLogica);
			    } 
	}
		}}
}