package com.example.studentcsvpdf.controller;

import com.example.studentcsvpdf.model.StudentDTO;
import com.example.studentcsvpdf.model.StudentResponse;
import com.example.studentcsvpdf.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequiredArgsConstructor
@Log4j2
public class StudentController
    {
        private final StudentService studentService;

        @PostMapping(path ="/student")
        public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentDTO studentDTO)
        {
            StudentResponse studentResponse=studentService.createStudent(studentDTO);
            log.info("Post SuccessFull");
            return new ResponseEntity<>(studentResponse, HttpStatus.ACCEPTED);
        }

        @GetMapping(path = "/student/{id}")
        public ResponseEntity<StudentDTO> getbyId(@PathVariable Long id)
        {
            StudentDTO studentDTO=studentService.getById(id);
            return new ResponseEntity<>(studentDTO,HttpStatus.FOUND);
        }

        @GetMapping(path = "/Student")
        public ResponseEntity<String> getCsvDownload()
        {
            studentService.getCsv();
            return ResponseEntity.ok().body("File Downloaded check ");
        }

        @GetMapping(path = "/export-Csv" )
        public void exportIntoCsv(HttpServletResponse response) throws IOException
        {
            response.setContentType("text/csv");
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=student.csv");
            studentService.studentCsv(studentService.findAll(),response.getWriter());

        }

        @GetMapping(path = "/export-pdf")
        public void exportToPdf(HttpServletResponse httpServletResponse) throws IOException
        {
            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=studentPdf.pdf");
            studentService.studentPdf(httpServletResponse);
        }

    }
