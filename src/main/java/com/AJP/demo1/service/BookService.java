package com.AJP.demo1.service;

import com.AJP.demo1.entity.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAllBooks();
    Optional<Book> findBookById(Long id);
    Book saveBook(Book book);
    void deleteBookById(Long id);
}