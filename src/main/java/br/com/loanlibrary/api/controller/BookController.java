package br.com.loanlibrary.api.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.loanlibrary.api.dto.BookFormDto;
import br.com.loanlibrary.model.entity.Book;
import br.com.loanlibrary.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

	private BookService bookService;
	private ModelMapper modelMapper;
	private static final String MSG_ERRO_LIVRO_NAO_ENCONTRADO = "Livro não encontrado.";
	
	@Autowired
	public BookController(BookService service, ModelMapper mapper) {
		this.bookService = service;
		this.modelMapper = mapper;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createBook(@RequestBody @Valid BookFormDto bookDto) {		
		
		Book book = modelMapper.map(bookDto, Book.class);
						
		Book bookEntity = bookService.save(book);
		
		BookFormDto bookResponse = modelMapper.map(bookEntity, BookFormDto.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(bookDto);
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public BookFormDto getBook(@PathVariable("id") Long id) {			
		return bookService.getById(id)
				.map(book -> modelMapper.map(book, BookFormDto.class))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, MSG_ERRO_LIVRO_NAO_ENCONTRADO));
	}
}
