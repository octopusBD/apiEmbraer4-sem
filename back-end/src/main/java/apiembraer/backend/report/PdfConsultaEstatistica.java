package apiembraer.backend.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
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

import apiembraer.backend.entity.ViewEstatisticaStatusEntity;
import apiembraer.backend.entity.ViewListarLogicaEntity;


public class PdfConsultaEstatistica {

	public static ByteArrayInputStream exportarPdfConsulta (List<ViewListarLogicaEntity> viewsampleestatistica) throws IOException {
		
		//Criando o documento PDF
	Document document = new Document(PageSize.A4.rotate(), 25, 25, 25, 25);
	ByteArrayOutputStream out = new ByteArrayOutputStream();

	try {
		//Criando a tabela para o relatório
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		table.setWidths(new int[] { 4, 4, 4 });
		
		
		
		//Criando o cabeçalho da tabela
		Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.WHITE);

		PdfPCell hcell;

		hcell = new PdfPCell(new Phrase("Chassi", headFont));
		hcell.setPaddingTop(9f);
		hcell.setFixedHeight(40f);
		hcell.setVerticalAlignment(Element.ALIGN_CENTER);
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);
		
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
		
		for (ViewListarLogicaEntity ViewSampleEntity : viewsampleestatistica) {

			//Povoando as células da tabela
			Font font = FontFactory.getFont(FontFactory.HELVETICA, 14, BaseColor.BLACK);
			Font fontDataHora = FontFactory.getFont(FontFactory.HELVETICA, 14, BaseColor.BLACK);

			PdfPCell cell;

			cell = new PdfPCell(new Phrase(ViewSampleEntity.getChassi(), font));
			cell.setPaddingTop(4f);
			cell.setFixedHeight(25f);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase(ViewSampleEntity.getItemNome(), font));
			cell.setPaddingTop(4f);
			cell.setFixedHeight(25f);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(ViewSampleEntity.getStatusSample(), font));
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
		
		//Definindo a cor do background e do grid do cabeçalho
		for(PdfPCell c: table.getRow(0).getCells()) {
			//c.setBackgroundColor(new BaseColor(37, 51, 129));
			c.setBackgroundColor(new BaseColor(0, 103, 181));
			c.setBorderColor(new BaseColor(0, 103, 181));
		}
		
		//Determinando a repetição do cabeçalho em todas as páginas geradas
		table.setHeaderRows(1);

		//Abrindo o documento PDF para ser editado
		PdfWriter.getInstance(document, out);
		document.open();
		
		//Adicionando imagem
									
		Image img = Image.getInstance("https://raw.githubusercontent.com/octopusBD/docs/main/api4sem/logo1png.png");
		img.setAbsolutePosition(635f, PageSize.A4.getHeight() - img.getScaledHeight() - 75);
		//img.scaleAbsolute(175f, 115f);
		img.scaleAbsolute(175f, 115f);
		//img.setSpacingAfter(25f);
		//Definindo parágrafos para o título do documento PDF
		//Paragraph textoEstacao = new Paragraph(new Phrase("Chassi " + viewsample.get(0).getChassi(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.BLACK)));
		//textoEstacao.setAlignment(Element.ALIGN_LEFT);

		//Paragraph textoDataHora = new Paragraph(new Phrase("Dados de " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(viewsample.get(0).getDatahoraCaptacao()) + " até " +new SimpleDateFormat("dd/MM/yyyy HH:mm").format(viewsample.get(viewsample.size() - 1).getDatahoraCaptacao()),FontFactory.getFont(FontFactory.HELVETICA, 21, BaseColor.BLACK)));
		//textoDataHora.setAlignment(Element.ALIGN_LEFT);

		Paragraph textoDados = new Paragraph(new Phrase("User - " + viewsampleestatistica.get(0).getNomeUsuario(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK)));
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
