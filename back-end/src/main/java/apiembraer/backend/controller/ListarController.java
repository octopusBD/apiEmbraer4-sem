package apiembraer.backend.controller;

import com.lowagie.text.DocumentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import apiembraer.backend.entity.ViewSampleEntity;
import apiembraer.backend.service.ListarService;
import apiembraer.backend.util.PDFGenerator;

import java.io.File;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/consultor")
public class ListarController {

    @Autowired
    ListarService ListarService;

    @GetMapping("/{idUsuario}")
    @ResponseBody
    public List<ViewSampleEntity> getViewSampleEntities(@PathVariable Integer idUsuario) {
        List<ViewSampleEntity> result = ListarService.getViewSampleEntities(idUsuario);
        System.err.println(result);
        return result;
    }

    @GetMapping("/pdf/{idUsuario}")
    public ResponseEntity<FileSystemResource> generatePDF(@PathVariable Integer idUsuario) {
        List<ViewSampleEntity> entities = ListarService.getViewSampleEntities(idUsuario);
		System.err.println(entities);
        String outputFilePath = "relatorio.pdf";
        try {
            PDFGenerator.generatePDF(entities, outputFilePath);
            File file = new File(outputFilePath);
            FileSystemResource resource = new FileSystemResource(file);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"");
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .body(resource);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}