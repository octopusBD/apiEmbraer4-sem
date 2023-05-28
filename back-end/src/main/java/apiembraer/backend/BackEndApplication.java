package apiembraer.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import apiembraer.backend.generator.GerarFormula;
import apiembraer.backend.repository.FormulaRepository;
import apiembraer.backend.repository.LogicaRepository;
import apiembraer.backend.repository.SampleRepository;
import apiembraer.backend.repository.ViewListarSampleRepository;

@SpringBootApplication
public class BackEndApplication {
	

	public static void main(String[] args) {
	    SpringApplication.run(BackEndApplication.class, args);
	}

	
}