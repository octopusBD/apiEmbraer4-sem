package apiembraer.backend.controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import apiembraer.backend.entity.ViewSampleEntity;
import apiembraer.backend.repository.ViewSampleRepository;
import apiembraer.backend.service.ListarService;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/consultor")
public class ListarController {

    @Autowired
    ListarService ListarService;

    @Autowired
    ViewSampleRepository viewSampleRepository;

    @GetMapping("/{idUsuario}")
    @ResponseBody
    public List<ViewSampleEntity> getViewSampleEntities(@PathVariable Integer idUsuario) {
        List<ViewSampleEntity> result = ListarService.getViewSampleEntities(idUsuario);
        System.err.println(result);
        return result;
    }

    @GetMapping("/pdf/{idUsuario}")
    public ResponseEntity<InputStreamResource> generatePDF(
        @PathVariable
        Integer idUsuario, HttpServletResponse response
    ) throws IOException, DocumentException {
        // Get data from database
        List<ViewSampleEntity> entities = viewSampleRepository.findAllByIdUsuario(idUsuario);
    
        // Create document
        com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
    
        // Add title to document
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 16);
        Paragraph title = new Paragraph("Itens", font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);
    
        // Create table
        PdfPTable table = new PdfPTable(new float[]{3, 2});
        table.setWidthPercentage(100);
        table.setSpacingBefore(20f);
        table.setSpacingAfter(20f);
    
        // Add headers to table
        PdfPCell cell;
        cell = new PdfPCell(new Paragraph("Item", font));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("Status", font));
        table.addCell(cell);

        //Alternando a cor do background e do grid das células entre branco e cinza
        boolean b = true;
        for(PdfPRow r: table.getRows()) {
            for(PdfPCell c: r.getCells()) {
                c.setBackgroundColor(b ? BaseColor.LIGHT_GRAY : BaseColor.WHITE);
                c.setBorderColor(b ? BaseColor.LIGHT_GRAY : BaseColor.WHITE);
            }
            b = !b;
        }

        for(PdfPCell c: table.getRow(0).getCells()) {
            c.setBackgroundColor(new BaseColor(13, 74, 153));
            c.setBorderColor(new BaseColor(13, 74, 153));
        }

        //Adicionando imagem
        Image img = Image.getInstance("https://github.com/octopusBD/apiEmbraer4-sem/blob/main/Imagens%20documentacao/doc/inicial.png");
        img.setAbsolutePosition(635f, PageSize.A4.getHeight() - img.getScaledHeight() - 90);
        img.scaleAbsolute(175f, 115f);

        Paragraph pulaLinha = new Paragraph(new Phrase(
            " ", FontFactory.getFont(FontFactory.HELVETICA, 18, BaseColor.BLACK)
        ));

        //Determinando a repetição do cabeçalho em todas as páginas geradas
        table.setHeaderRows(1);

        // Add data to table
        for (ViewSampleEntity entity : entities) {
            table.addCell(entity.getItem().toString());
            table.addCell(entity.getStatusSample().toString());
        }
    
        // Add table to document
        document.add(img);
        document.add(pulaLinha);
        document.add(pulaLinha);
        document.add(table);
        document.close();
    
        // Return response with PDF
        java.io.ByteArrayInputStream in = new java.io.ByteArrayInputStream(out.toByteArray());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=relatorio.pdf");
    
        return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(in));
    }    
}
