package com.thiagoprioto.bookstoremenager.controller;

import com.thiagoprioto.bookstoremenager.dto.MessegeResponseDTO;
import com.thiagoprioto.bookstoremenager.entity.Book;
import com.thiagoprioto.bookstoremenager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping
    public MessegeResponseDTO create(@RequestBody Book book){
       return bookService.create(book);
    }
}
