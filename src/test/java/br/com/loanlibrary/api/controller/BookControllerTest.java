package br.com.loanlibrary.api.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.loanlibrary.api.dto.BookFormDto;
import br.com.loanlibrary.api.exception.BusinessException;
import br.com.loanlibrary.model.entity.Book;
import br.com.loanlibrary.service.BookService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BookControllerTest {
	
	private static final String URL_API = "/api/books";

	private static final String MSG_ERRO_ISBN_DUPLICADO = "ISBN já cadastrado.";

	private static final String MSG_ERRO_LIVRO_NAO_ENCONTRADO = "Livro não encontrado.";
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	BookService bookService;
	
	@Test
	@DisplayName("Deve criar um novo livro com sucesso.")
	public void createNewBookTest() throws Exception {
		// cenario
		String payload = new ObjectMapper().writeValueAsString(getBookDto());
		
		// execucao
		BDDMockito.given(bookService.save(Mockito.any(Book.class))).willReturn(getBookEntity());
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(URL_API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(payload);
		
		log.info("Payload Entrada: {}", payload);
				
		// validacao
		mvc.perform(request)
			.andExpect(status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("codigo").value(getBookDto().getCodigo()))
			.andExpect(MockMvcResultMatchers.jsonPath("titulo").value(getBookDto().getTitulo()))
			.andExpect(MockMvcResultMatchers.jsonPath("autor").value(getBookDto().getAutor()))
			.andExpect(MockMvcResultMatchers.jsonPath("isbn").value(getBookDto().getIsbn()));
	}

	@Test
	@DisplayName("Deve lancar uma exception ao tentar criar um livro com dados invalidos.")
	public void createInvalidBookTest() throws Exception {
		// cenario
		String payload = new ObjectMapper().writeValueAsString(new BookFormDto());
		
		// execucao
		BDDMockito.given(bookService.save(Mockito.any(Book.class))).willReturn(getBookEntity());
		log.info("Payload Entrada: {}", payload);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(URL_API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(payload);		
				
		// validacao
		mvc.perform(request)
			.andExpect(status().isBadRequest())
			.andExpect(MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(3)));
	}
	
	@Test
	@DisplayName("Deve lancar uma exception ao tentar criar um livro com ISBN já cadastrado anterior.")
	public void createBookWithIsbnDuplicadoTest() throws Exception {
		// cenario
		String payload = new ObjectMapper().writeValueAsString(getBookDto());
		
		// execucao
		BDDMockito.given(bookService.save(Mockito.any(Book.class))).willThrow(new BusinessException(MSG_ERRO_ISBN_DUPLICADO));
		log.info("Payload Entrada: {}", payload);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(URL_API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(payload);		
				
		// validacao
		mvc.perform(request)
			.andExpect(status().isBadRequest())
			.andExpect(MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(1)))
			.andExpect(MockMvcResultMatchers.jsonPath("errors[0]").value(MSG_ERRO_ISBN_DUPLICADO));
	}
	
	
	@Test
	@DisplayName("Deve retornar os detalhes de um livro já cadastrado.")
	public void getRetornarUmBookByIdTest() throws Exception {
		// cenario
		Long idBook = getBookDto().getCodigo();
		
		Book book = getBookEntity();
		
		// execucao
		BDDMockito.given(bookService.getById(idBook)).willReturn(Optional.of(book));
		
		log.info("Payload Retorno: {}", book);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(URL_API.concat("/"+idBook))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);				
				
		// validacao
		mvc.perform(request)
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("codigo").value(getBookDto().getCodigo()))
			.andExpect(MockMvcResultMatchers.jsonPath("titulo").value(getBookDto().getTitulo()))
			.andExpect(MockMvcResultMatchers.jsonPath("autor").value(getBookDto().getAutor()))
			.andExpect(MockMvcResultMatchers.jsonPath("isbn").value(getBookDto().getIsbn()));
	}
	
	@Test
	@DisplayName("Deve retornar uma exception NOT_FOUND de livro nao encontrado.")
	public void getDeveRetornarUmaExceptionNotFoundBookByNaoEncontradoIdTest() throws Exception {
		// cenario
		Long idBook = getBookDto().getCodigo();
				
		// execucao
		BDDMockito.given(bookService.getById(idBook)).willReturn(Optional.empty());
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(URL_API.concat("/"+idBook))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);				
				
		// validacao
		mvc.perform(request)
			.andExpect(status().isNotFound());
	}
	
	private Book getBookEntity() {		
		return Book.builder()
				.codigo(1L)
				.titulo("Aprenda programar em uma semana")
				.autor("Gisiona")
				.isbn("123456")
				.dataCadastro(LocalDateTime.now())
				.build();
	}
	
	private BookFormDto getBookDto() {
		return BookFormDto
				.builder()
				.codigo(1L)
				.titulo("Aprenda programar em uma semana")
				.autor("Gisiona")
				.isbn("123456")				
			.build();
	}

}
