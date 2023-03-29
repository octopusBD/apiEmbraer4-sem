package apiembraer.backend.util;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import apiembraer.backend.entity.ViewSampleEntity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class PDFGenerator {

    public static void generatePDF(List<ViewSampleEntity> entities, String filename) throws FileNotFoundException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream("relatorio.pdf"));
        document.open();
        for (ViewSampleEntity entity : entities) {
            document.add(new Paragraph("ID do Chassi: " + entity.getIdChassi()));
            document.add(new Paragraph("ID do Boletim: " + entity.getIdBoletim()));
            document.add(new Paragraph("Nome do Usuário: " + entity.getNomeUsuario()));
            document.add(new Paragraph("Boletim: " + entity.getBoletim()));
            document.add(new Paragraph("Item: " + entity.getItem()));
            document.add(new Paragraph("Status da Amostra: " + entity.getStatusSample()));
            document.add(new Paragraph("Chassi: " + entity.getChassi()));
            document.add(new Paragraph("Último usuário que alterou: " + entity.getUltUsuAlt()));
            document.add(new Paragraph("Data da última edição: " + entity.getDtUltEdicao()));
            document.add(new Paragraph("\n"));
        }
        document.close();
    }
}