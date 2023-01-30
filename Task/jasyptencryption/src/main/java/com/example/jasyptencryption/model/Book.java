package com.example.jasyptencryption.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book
    {
        private String bookName;
        private List<Author> authorEntity;
    }
