package com.example.jasyptencryption.controller;

import com.example.jasyptencryption.model.Book;
import com.example.jasyptencryption.model.BookResponse;
import com.example.jasyptencryption.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController
    {
       private final BookService bookService;

        @PostMapping(value ="/books")
        public ResponseEntity<BookResponse> addBook(@RequestBody Book book)
        {
            BookResponse bookResponse=bookService.addBook(book);
            return new ResponseEntity<>(bookResponse, HttpStatus.OK);
        }
        @GetMapping(value = "/books/{id}")
        public ResponseEntity<Book> getById(@PathVariable Long id)
        {
            Book book=bookService.getById(id);
            return new ResponseEntity<>(book,HttpStatus.OK);
        }

        @GetMapping(value = "/books")
        public ResponseEntity<List<Book>> allBooks()
        {
           List<Book>  book=bookService.allBooks();
            return new ResponseEntity<>(book,HttpStatus.OK);
        }

        @DeleteMapping(value = "/books/{id}")
        public ResponseEntity<Void> deleteById(@PathVariable Long id)
        {
            bookService.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }