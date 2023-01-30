package com.example.imageupload.service;

import com.example.imageupload.entity.ImageEntity;
import com.example.imageupload.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService
    {
        private final ImageRepository imageRepository;

        public void uploadImage(MultipartFile file) throws IOException
        {
            ImageEntity imageEntity =new ImageEntity();
            imageEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
            imageEntity.setContentType(file.getContentType());
            imageEntity.setData(file.getBytes());
            imageEntity.setSize(file.getSize());
            imageRepository.save(imageEntity);
        }


        public Optional<ImageEntity> getImage(Long id)
        {


            return imageRepository.findById(id);
        }


        public byte[] getPdf(Long id)  {


         return imageRepository.findById(id).get().getData();

            }


    }
