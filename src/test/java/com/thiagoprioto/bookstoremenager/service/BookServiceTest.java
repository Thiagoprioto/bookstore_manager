package com.thiagoprioto.bookstoremenager.service;

import com.thiagoprioto.bookstoremenager.dto.BookDTO;
import com.thiagoprioto.bookstoremenager.entity.Book;
import com.thiagoprioto.bookstoremenager.repository.BookRepository;
import com.thiagoprioto.bookstoremenager.utils.BookUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.thiagoprioto.bookstoremenager.utils.BookUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    private MockMvc mockMvc;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void whenGivenExistingIdThenReturnThisBook() {
        Book expectedFoundBook = createFakeBook();
        when(bookRepository.findById(expectedFoundBook.getId())).thenReturn(Optional.of(expectedFoundBook));

        BookDTO bookDTO = bookService.findById(expectedFoundBook.getId());

        assertEquals(expectedFoundBook.getName(), bookDTO.getName());
        assertEquals(expectedFoundBook.getIsbn(), bookDTO.getIsbn());
        assertEquals(expectedFoundBook.getPublisherName(), bookDTO.getPublisherName());
    }
}
