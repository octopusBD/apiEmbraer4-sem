package apiembraer.backend.util;

/*import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.List;

import apiembraer.backend.entity.ViewSampleEntity;

import org.springframework.data.repository.query.Param;

public class PDFGenerator {
    public static ByteArrayInputStream generatePDF(List<ViewSampleEntity> entities) throws IOException {

        Document document = new Document(
            PageSize.A4.rotate(), 25, 25, 25, 25
        );
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            //Criando a tabela para o relatório
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(new int[] { 4, 4});

            //Criando o cabeçalho da tabela
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.WHITE);

            PdfPCell hcell;

            hcell = new PdfPCell(new Phrase("Item", headFont));
            hcell.setPaddingTop(9f);
            hcell.setFixedHeight(40f);
            hcell.setVerticalAlignment(Element.ALIGN_CENTER);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Status", headFont));
            hcell.setPaddingTop(9f);
            hcell.setFixedHeight(40f);
            hcell.setVerticalAlignment(Element.ALIGN_CENTER);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (ViewSampleEntity entity : entities) {
                Font font = FontFactory.getFont(FontFactory.HELVETICA, 14, BaseColor.BLACK);

                PdfPCell cell;

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
            }

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

            //Determinando a repetição do cabeçalho em todas as páginas geradas
            table.setHeaderRows(1);

            //Abrindo o documento PDF para ser editado
            PdfWriter.getInstance(document, out);
            document.open();

            //Adicionando imagem
            Image img = Image.getInstance("https://raw.githubusercontent.com/octopusBD/docs/main/api4sem/logo1png.png");
            img.setAbsolutePosition(635f, PageSize.A4.getHeight() - img.getScaledHeight() - 90);
            img.scaleAbsolute(175f, 115f);

            //Definindo parágrafos para o título do documento PDF
            Paragraph textoDados = new Paragraph(new Phrase("Sample - " + "Chassi " + entities.get(0).getChassi(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK)));
            textoDados.setAlignment(Element.ALIGN_CENTER);

            Paragraph pulaLinha = new Paragraph(new Phrase(" ", FontFactory.getFont(FontFactory.HELVETICA, 18, BaseColor.BLACK)));

            //Adicionando os parágrafos, imagem e tabela ao documento PDF
            document.add(img);
            //document.add(textoEstacao);
            //document.add(textoDataHora);
            document.add(pulaLinha);
            document.add(textoDados);
            document.add(pulaLinha);
            document.add(table);
            document.close();

        } catch (DocumentException ex) {

        }
        
        return new ByteArrayInputStream(out.toByteArray());
        
    }
}
*/