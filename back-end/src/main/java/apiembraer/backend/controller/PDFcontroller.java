package apiembraer.backend.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apiembraer.backend.entity.ViewEstatisticaStatusEntity;
import apiembraer.backend.entity.ViewSampleEntity;
import apiembraer.backend.report.PdfConsulta;
import apiembraer.backend.report.PdfConsultaEstatistica;
import apiembraer.backend.service.ListarService;

@CrossOrigin
@RestController
@RequestMapping(value = "/pdf")
public class PDFcontroller {

    @Autowired
    ListarService ListarService;

    //Este método retorna um relatório em formato PDF contendo informações das amostras de um determinado usuário e chassi que foram consultadas.
	@GetMapping(value = "/{idUsuario}/{chassi}", produces = MediaType.APPLICATION_PDF_VALUE)

	public ResponseEntity<InputStreamResource> relatorioConsulta (
		HttpServletResponse response, 
		@PathVariable("idUsuario") Integer idUsuario, 
		@PathVariable("chassi") String chassi
	) throws IOException {

		// Chama o método que retorna a lista de amostras da consulta
		List<ViewSampleEntity> result = ListarService.getViewSampleConsulta(idUsuario, chassi);
		
		// Gera o relatório em PDF com base na lista de amostras retornada
        ByteArrayInputStream bis = PdfConsulta.exportarPdfConsulta(result);

		HttpHeaders headers = new HttpHeaders();

		// Define o cabeçalho HTTP com o nome do arquivo do relatório a ser baixado
		headers.add("Content-Disposition", "attachment;filename=RelatorioConsulta.pdf");

		// Retorna a resposta HTTP com o conteúdo do relatório em PDF
		return ResponseEntity
			.ok()
			.headers(headers)
			.contentType(MediaType.APPLICATION_PDF)
			.body(new InputStreamResource(bis));

    }
	
    
	@GetMapping(value = "estatistica/{nomeUsuario}", produces = MediaType.APPLICATION_PDF_VALUE)
	
		public ResponseEntity<InputStreamResource> relatorioConsultaa (
			HttpServletResponse response, 
			@PathVariable("nomeUsuario") String nomeUsuario
		) throws IOException {

		// Chama o método que retorna a lista de amostras da consulta
		List<ViewEstatisticaStatusEntity> result = ListarService.getViewSampleConsultaa(nomeUsuario);
		
		// Gera o relatório em PDF com base na lista de amostras retornada
        ByteArrayInputStream bis = PdfConsultaEstatistica.exportarPdfConsulta(result);

		HttpHeaders headers = new HttpHeaders();

		// Define o cabeçalho HTTP com o nome do arquivo do relatório a ser baixado
		headers.add("Content-Disposition", "attachment;filename=RelatorioConsulta.pdf");

		// Retorna a resposta HTTP com o conteúdo do relatório em PDF
		return ResponseEntity
			.ok()
			.headers(headers)
			.contentType(MediaType.APPLICATION_PDF)
			.body(new InputStreamResource(bis));

    }
}
