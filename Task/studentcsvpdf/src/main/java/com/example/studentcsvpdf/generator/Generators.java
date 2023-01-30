package com.example.studentcsvpdf.generator;

import com.example.studentcsvpdf.entity.StudentEntity;
import com.example.studentcsvpdf.repository.StudentRepositpory;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
@RequiredArgsConstructor
@Component
public class Generators {

    private final StudentRepositpory studentRepositpory;


    public void studentCsv(List<StudentEntity> studentEntityList, Writer writer){

        try{
            CSVPrinter printer =new CSVPrinter(writer, CSVFormat.DEFAULT);
            for (StudentEntity studentEntity:studentEntityList)
            {
                printer.printRecord(studentEntity.getId(),studentEntity.getFirstName(),studentEntity.getLastName(),studentEntity.getMarks());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void studentPdf(HttpServletResponse httpServletResponse) throws IOException {

        Document document=new Document(PageSize.A4);

        PdfWriter.getInstance(document,httpServletResponse.getOutputStream());

        document.open();
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);
        Paragraph paragraph1 = new Paragraph("List of the Students", fontTiltle);
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph1);

        PdfPTable table = new PdfPTable(4);

        table.setWidthPercentage(100);
        table.setWidths(new int[] {2,2,2,2});
        table.setSpacingBefore(3);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(CMYKColor.green);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("First Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Last Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Marks", font));
        table.addCell(cell);

        List<StudentEntity> studentEntityList=studentRepositpory.findAll();

        for (StudentEntity studentEntity: studentEntityList)
        {
            table.addCell(String.valueOf(studentEntity.getId()));
            table.addCell(studentEntity.getFirstName());
            table.addCell(studentEntity.getLastName());
            table.addCell(String.valueOf(studentEntity.getMarks()));
        }
        document.add(table);
        document.close();



//
    }


}
