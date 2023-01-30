package com.example.studentcsvpdf.service;

import com.example.studentcsvpdf.entity.StudentEntity;
import com.example.studentcsvpdf.generator.Generators;
import com.example.studentcsvpdf.mapper.StudentMapper;
import com.example.studentcsvpdf.model.StudentDTO;
import com.example.studentcsvpdf.model.StudentResponse;
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
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class StudentService
    {
        private final StudentRepositpory studentRepositpory;
        private final StudentMapper studentMapper;

        private final Generators generators;

        public StudentResponse createStudent(StudentDTO studentDTO) {

            StudentEntity studentEntity=  studentMapper.modelToEntity(studentDTO);
            studentRepositpory.save(studentEntity);
            StudentResponse studentResponse =new StudentResponse();
            studentResponse.setId(studentEntity.getId());
            log.info("Posting .......");
            return studentResponse;

        }


        public StudentDTO getById(Long id) {
            Optional<StudentEntity> studentEntity=studentRepositpory.findById(id);
            StudentDTO studentDTO=new StudentDTO();
            if (studentEntity.isPresent()){
                studentDTO= studentMapper.entityToModel(studentEntity.get());
            }
            else {
                log.info("id not found");
            }
            return studentDTO;

        }

        public void getCsv()
        {
            studentRepositpory.getCSV();
        }

        public List<StudentEntity> findAll()
        {
            return studentRepositpory.findAll();

        }


        public void studentCsv(List<StudentEntity> all, PrintWriter writer) {
             generators.studentCsv(all,writer);

        }

        public void studentPdf(HttpServletResponse httpServletResponse) throws IOException {
            generators.studentPdf(httpServletResponse);
        }
    }



