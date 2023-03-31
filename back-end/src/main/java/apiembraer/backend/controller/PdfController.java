package apiembraer.backend.controller;
/* 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import apiembraer.backend.entity.ViewSampleEntity;
import apiembraer.backend.service.ListarService;
import apiembraer.backend.util.PDFGenerator;

import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value="/pdf")
public class PdfController {
    @Autowired
    ListarService ListarService;

    @Autowired
    PDFGenerator pfdGenerator;

    @GetMapping
    (value = "/{idUsuario}/{chassi}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> relatorioConsulta
        (HttpServletResponse response, @PathVariable("idUsuario") Integer idUsuario,
    @PathVariable("chassi") String chassi) throws IOException {

        List<ViewSampleEntity> result = ListarService.getViewSampleConsulta(idUsuario, chassi);
        ByteArrayInputStream bis = PDFGenerator.generatePDF(result);

        HttpHeaders headers = new HttpHeaders();

       //headers.add("Content-Disposition", "attachment;filename=Relatório Precipitação " + estNome + "(" + new SimpleDateFormat("dd-MM-yyyy").format(precipitacao.get(0).getDatahoraCaptacao()) + " até " + new SimpleDateFormat("dd-MM-yyyy").format(precipitacao.get(precipitacao.size() - 1).getDatahoraCaptacao()) +").pdf");
		headers.add("Content-Disposition", "attachment;filename=RelatorioConsulta.pdf");

		return ResponseEntity.ok().headers(headers).contentType(
            MediaType.APPLICATION_PDF).body(new InputStreamResource(bis)
        );
    }
}
*/