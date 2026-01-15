package com.thiagoprioto.bookstoremenager.service;

import com.thiagoprioto.bookstoremenager.dto.BookDTO;
import com.thiagoprioto.bookstoremenager.dto.MessageResponseDTO;
import com.thiagoprioto.bookstoremenager.entity.Book;
import com.thiagoprioto.bookstoremenager.exception.BookNotFoundException;
import com.thiagoprioto.bookstoremenager.mapper.BookMapper;
import com.thiagoprioto.bookstoremenager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private BookRepository bookRepository;

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public MessageResponseDTO create(BookDTO bookDTO){
        Book bookToSave = bookMapper.toModel(bookDTO);

        Book savedBook = bookRepository.save(bookToSave);
        return MessageResponseDTO.builder()
                .message("Book created with ID " + savedBook.getId()).build();
    }

    public BookDTO findById(Long id) throws BookNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        return bookMapper.toDTO(book);
    }

    public MessageResponseDTO updateById(Long id, BookDTO bookDTO) throws BookNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        bookDTO.setId(id);

        Book bookToUpdate = bookMapper.toModel(bookDTO);
        Book bookToSave = bookRepository.save(bookToUpdate);

        return MessageResponseDTO.builder().message("Book has been updated!" + bookToUpdate.getId()).build();
    }
}
