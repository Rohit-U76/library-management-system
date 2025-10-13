package com.AJP.demo1.controller;

import com.AJP.demo1.entity.IssueRecord;
import com.AJP.demo1.service.BookService;
import com.AJP.demo1.service.IssueRecordService;
import com.AJP.demo1.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/issues")
public class IssueRecordController {

    private final IssueRecordService issueRecordService;
    private final BookService bookService; // Needed for dropdown list
    private final UserService userService; // Needed for dropdown list

    @Autowired
    public IssueRecordController(IssueRecordService issueRecordService, BookService bookService, UserService userService) {
        this.issueRecordService = issueRecordService;
        this.bookService = bookService;
        this.userService = userService;
    }

    // List all issue records
    @GetMapping
    public String listIssueRecords(Model model) {
        model.addAttribute("issues", issueRecordService.findAllIssueRecords());
        model.addAttribute("currentDate", LocalDate.now());
        return "issues"; // resources/templates/issues.html
    }

    // Show Issue Form (Create)
    @GetMapping("/new")
    public String showIssueForm(Model model) {
        model.addAttribute("issueRecord", new IssueRecord());
        model.addAttribute("books", bookService.findAllBooks());
        model.addAttribute("users", userService.findAllUsers());
        return "issue-form"; // resources/templates/issue-form.html
    }

    // Process Issue Submission
    @PostMapping
    public String saveIssue(@Valid @ModelAttribute("issueRecord") IssueRecord issueRecord, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("books", bookService.findAllBooks());
            model.addAttribute("users", userService.findAllUsers());
            return "issue-form";
        }
        issueRecordService.saveIssueRecord(issueRecord);
        // In a real app, you would also decrement the book quantity here
        return "redirect:/issues";
    }

    // Process Book Return
    @GetMapping("/return/{id}")
    public String returnBook(@PathVariable("id") Long id) {
        issueRecordService.processReturn(id);
        return "redirect:/issues";
    }
}