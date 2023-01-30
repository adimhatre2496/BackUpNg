package com.example.imageupload.controller;

import com.example.imageupload.entity.ImageEntity;
import com.example.imageupload.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ImageController
        {
                private final ImageService imageService;

                @PostMapping(path = "/images")
                public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
                        try {
                                 imageService.uploadImage(file);
                                 return ResponseEntity.status(HttpStatus.OK)
                                         .body("File uploaded successfully");
                        }
                        catch (Exception e){
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                        .body("File not found");
                        }

                }

                @GetMapping(path = "/images/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
                public ResponseEntity<byte[]> getfile (@PathVariable Long id)
                {
                        Optional<ImageEntity> imageEntity = imageService.getImage(id);
                        if (imageEntity.isEmpty())
                        {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                        .build();
                        }
                        return ResponseEntity.status(HttpStatus.FOUND)
                                .body(imageEntity.get().getData());
                }

                @GetMapping(path = "/images-pdf/{id}")
                public ResponseEntity<byte[]> getfilePdf (@PathVariable Long id)
                {/*
                        HttpHeaders headers = new HttpHeaders();
                        headers.add("Content-Disposition", "attachment; filename=image.pdf");
                        headers.add("Content-Type", "application/pdf");
                       byte[]image =imageService.getPdf(id);
                        return new ResponseEntity<>(image,HttpStatus.OK);*/
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_PDF_VALUE));
                        return new ResponseEntity<>(imageService.getPdf(id),headers,HttpStatus.OK);
                }
        }
