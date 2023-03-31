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

import apiembraer.backend.entity.ViewSampleEntity;
import apiembraer.backend.report.PdfConsulta;
import apiembraer.backend.service.ListarService;

@CrossOrigin
@RestController
@RequestMapping(value = "/pdf")
public class PDFcontroller {

    @Autowired
    ListarService ListarService;

	@GetMapping(value = "/{idUsuario}/{chassi}", produces = MediaType.APPLICATION_PDF_VALUE)

	public ResponseEntity<InputStreamResource> relatorioConsulta (
		HttpServletResponse response, 
		@PathVariable("idUsuario") Integer idUsuario, 
		@PathVariable("chassi") String chassi
	) throws IOException {

		List<ViewSampleEntity> result = ListarService.getViewSampleConsulta(idUsuario, chassi);
        ByteArrayInputStream bis = PdfConsulta.exportarPdfConsulta(result);

		HttpHeaders headers = new HttpHeaders();

		//headers.add("Content-Disposition", "attachment;filename=Relatório Precipitação " + estNome + "(" + new SimpleDateFormat("dd-MM-yyyy").format(precipitacao.get(0).getDatahoraCaptacao()) + " até " + new SimpleDateFormat("dd-MM-yyyy").format(precipitacao.get(precipitacao.size() - 1).getDatahoraCaptacao()) +").pdf");
		headers.add("Content-Disposition", "attachment;filename=RelatorioConsulta.pdf");

		return ResponseEntity
			.ok()
			.headers(headers)
			.contentType(MediaType.APPLICATION_PDF)
			.body(new InputStreamResource(bis));

    }
}
