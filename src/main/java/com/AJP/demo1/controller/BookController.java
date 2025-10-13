package com.AJP.demo1.controller;

import com.AJP.demo1.entity.Book;
import com.AJP.demo1.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // LIST all books
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "books"; // Maps to resources/templates/books.html
    }

    // SHOW Add/Update Form
    @GetMapping("/new")
    public String showBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("isNew", true);
        return "book-form"; // Maps to resources/templates/book-form.html
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Optional<Book> book = bookService.findBookById(id);
        if (book.isEmpty()) {
            // A professional application would redirect with an error message
            return "redirect:/books";
        }
        model.addAttribute("book", book.get());
        model.addAttribute("isNew", false);
        return "book-form";
    }

    // PROCESS Form Submission
    @PostMapping
    public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isNew", book.getId() == null);
            return "book-form";
        }
        bookService.saveBook(book);
        return "redirect:/books";
    }

    // DELETE a book
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }
}