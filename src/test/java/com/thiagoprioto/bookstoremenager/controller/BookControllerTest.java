package com.thiagoprioto.bookstoremenager.controller;

import com.thiagoprioto.bookstoremenager.dto.BookDTO;
import com.thiagoprioto.bookstoremenager.dto.MessageResponseDTO;
import com.thiagoprioto.bookstoremenager.entity.Book;
import com.thiagoprioto.bookstoremenager.exception.BookNotFoundException;
import com.thiagoprioto.bookstoremenager.mapper.BookMapper;
import com.thiagoprioto.bookstoremenager.service.BookService;
import com.thiagoprioto.bookstoremenager.utils.BookUtils;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static com.thiagoprioto.bookstoremenager.utils.BookUtils.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    public static final String BOOK_API_URL_PATH = "/api/v1/books";
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    void WhenPOSTisCalledThenABookShouldBeCreated() throws Exception {
        BookDTO bookDTO = createFakeBookDTO();
        MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder()
                .message("Book created with ID " + bookDTO.getId())
                .build();

        //Import static
        when(bookService.create(bookDTO)).thenReturn(expectedMessageResponse);
        mockMvc.perform(post(BOOK_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Is.is(expectedMessageResponse.getMessage())));
    }

    @Test
    void WhenPOSTWhithInvalidISBNIsCalledThenBadRequestShouldBeReturned() throws Exception {
        BookDTO bookDTO = createFakeBookDTO();
        bookDTO.setIsbn("invalid isbn");

        mockMvc.perform(post(BOOK_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bookDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void WhenPUTisCalledThenABookshouldBeUpdated() throws Exception {
        // 1. CENÁRIO: Preparar os dados de teste
        BookDTO bookDTO = createFakeBookDTO();
        Long bookId = bookDTO.getId();

        // A mensagem deve ser EXATAMENTE igual à que o seu Service retorna
        MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder()
                .message("Book has been updated!" + bookId)
                .build();

        // 2. MOCK: Configurar o comportamento do Service
        when(bookService.updateById(eq(bookId), any(BookDTO.class)))
                .thenReturn(expectedMessageResponse);

        // 3. EXECUÇÃO E VERIFICAÇÃO: Simular a chamada HTTP PUT
        mockMvc.perform(put(BOOK_API_URL_PATH + "/" + bookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Is.is(expectedMessageResponse.getMessage())));
    }
}
