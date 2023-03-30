package apiembraer.backend.util;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.text.SimpleDateFormat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;

import apiembraer.backend.entity.ViewSampleEntity;

import org.springframework.data.repository.query.Param;

public class PDFGenerator {
    public static void generatePDF(
        List<ViewSampleEntity> entities, String filename
    ) throws FileNotFoundException, DocumentException {

        Document document = new Document(PageSize.A4.rotate(), 25, 25, 25, 25);
        PdfWriter.getInstance(document, new FileOutputStream("relatorio.pdf"));

        document.open();
        for (ViewSampleEntity entity : entities) {
            PdfPCell cell;

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(new int[] {4, 4});

            Font font = FontFactory.getFont(FontFactory.HELVETICA, 14, BaseColor.BLACK);

            cell = new PdfPCell(new Phrase(entity.getItem(), font));
            cell.setPaddingTop(4f);
            cell.setFixedHeight(25f);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(entity.getStatusSample(), font));
            cell.setPaddingTop(4f);
            cell.setFixedHeight(25f);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

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

            //Definindo a cor do background e do grid do cabeçalho
            for(PdfPCell c: table.getRow(0).getCells()) {
                c.setBackgroundColor(new BaseColor(13, 74, 153));
                c.setBorderColor(new BaseColor(13, 74, 153));
            }

            //Determinando a repetição do cabeçalho em todas as páginas geradas
            table.setHeaderRows(1);

            //Abrindo o documento PDF para ser editado
            PdfWriter.getInstance(document, out);
            document.open();

            Paragraph textoDados = new Paragraph(new Phrase(
                "PDF", FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK
                )
            ));
            textoDados.setAlignment(Element.ALIGN_CENTER);

            Paragraph pulaLinha = new Paragraph(new Phrase(
                " ", FontFactory.getFont(FontFactory.HELVETICA, 18, BaseColor.BLACK)
            ));

            document.add(pulaLinha);
            document.add(textoDados);
            document.add(pulaLinha);
            document.add(table);
        }
        document.close();
    }
}