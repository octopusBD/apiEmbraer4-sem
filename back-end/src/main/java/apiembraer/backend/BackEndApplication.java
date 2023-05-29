package apiembraer.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import apiembraer.backend.generator.GerarFormula;
import apiembraer.backend.repository.FormulaRepository;
import apiembraer.backend.repository.LogicaRepository;
import apiembraer.backend.repository.ViewListarSampleRepository;

@SpringBootApplication
public class BackEndApplication implements CommandLineRunner {
	
	@Autowired
	private ViewListarSampleRepository sampleRepository;

	@Autowired
	private FormulaRepository formulaRepository;
	
	@Autowired
	private LogicaRepository logicaRepository;
	
	
	public static void main(String[] args) {
	    SpringApplication.run(BackEndApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		GerarFormula gerarFormula = new GerarFormula(sampleRepository, formulaRepository, logicaRepository);
	    gerarFormula.processarSamples();
	}

	
}