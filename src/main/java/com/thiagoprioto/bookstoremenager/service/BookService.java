package com.thiagoprioto.bookstoremenager.service;

import com.thiagoprioto.bookstoremenager.dto.MessegeResponseDTO;
import com.thiagoprioto.bookstoremenager.entity.Book;
import com.thiagoprioto.bookstoremenager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public MessegeResponseDTO create(Book book){
        Book savedBook = bookRepository.save(book);
        return MessegeResponseDTO.builder()
                .message("Book created with ID " + savedBook.getId()).build();
    }
}
