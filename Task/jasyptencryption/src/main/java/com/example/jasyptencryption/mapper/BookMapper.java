package com.example.jasyptencryption.mapper;

import com.example.jasyptencryption.entity.BookEntity;
import com.example.jasyptencryption.model.Book;
import org.mapstruct.Mapper;
import java.util.List;
@Mapper(componentModel = "spring")
public interface BookMapper
    {
        BookEntity modelToEntity(Book book);
        Book entityToMode(BookEntity bookEntity);
        List<Book> entityToList(List<BookEntity> bookEntities);
    }
