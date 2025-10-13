package com.AJP.demo1.repository;

import com.AJP.demo1.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Custom repository methods can be added here if needed
}