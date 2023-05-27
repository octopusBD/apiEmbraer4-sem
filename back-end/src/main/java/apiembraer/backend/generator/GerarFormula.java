package apiembraer.backend.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import apiembraer.backend.entity.BoletimEntity;
import apiembraer.backend.entity.ChassiEntity;
import apiembraer.backend.entity.FormulaEntity;
import apiembraer.backend.entity.SampleEntity;
import apiembraer.backend.entity.ViewListarSampleEntity;
import apiembraer.backend.repository.FormulaRepository;
import apiembraer.backend.repository.SampleRepository;
import apiembraer.backend.repository.ViewListarSampleRepository;

public class GerarFormula {

	@Autowired
	private ViewListarSampleRepository sampleRepository;

	@Autowired
	private FormulaRepository formulaRepository;

	public GerarFormula(ViewListarSampleRepository sampleRepository, FormulaRepository formulaRepository) {
		this.sampleRepository = sampleRepository;
		this.formulaRepository = formulaRepository;
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
					break;
				}
			}

			if (formulaValida) {
				System.out.println("Item gerado para a fórmula '" + formulaEncontrada + "': Pneu");
			} else {
				System.out.println("Item não gerado para a fórmula '" + formulaEncontrada + "'");
			}
		}
	}

}