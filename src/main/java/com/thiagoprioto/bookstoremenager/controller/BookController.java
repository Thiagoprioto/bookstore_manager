package com.thiagoprioto.bookstoremenager.controller;

import com.thiagoprioto.bookstoremenager.dto.BookDTO;
import com.thiagoprioto.bookstoremenager.dto.MessageResponseDTO;
import com.thiagoprioto.bookstoremenager.exception.BookNotFoundException;
import com.thiagoprioto.bookstoremenager.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping
    public MessageResponseDTO create(@RequestBody @Valid BookDTO bookDTO){
       return bookService.create(bookDTO);
    }

    @GetMapping("/{id}")
    public BookDTO findById(@PathVariable Long id) throws BookNotFoundException {
        return bookService.findById(id);
    }
}
