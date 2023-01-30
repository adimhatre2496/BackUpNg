package com.example.customValidation.testUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Log4j2
public class MapUtils
    {
        public static String readFile(String filePath) throws IOException {
            String fileData = null;
            try {
                fileData = new String(Files.readAllBytes(Paths.get(filePath)));
            } catch (FileNotFoundException e) {
                log.error("File Not Found={}", filePath);
            } catch (IOException e) {
                log.error("File Reading Issue={}", filePath);
            }
            return fileData;
        }

        public static <T> T getObjectFromJsonFile(String content, Class<T> objectToCast) throws IOException {
            T object = null;
            try {
                object = new ObjectMapper().readValue(content, objectToCast);
            } catch (Exception e) {
                log.error("File not found for respected path with exception message={}", e.getMessage());
            }
            return object;
        }

    }
