package br.com.loanlibrary.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.loanlibrary.api.exception.BusinessException;
import br.com.loanlibrary.model.entity.Book;
import br.com.loanlibrary.model.repository.BookRepository;
import br.com.loanlibrary.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private BookRepository bookRepository;
	private static final String MSG_ERRO_ISBN_DUPLICADO = "ISBN já cadastrado.";
	
	public BookServiceImpl(BookRepository _bookRepository) {
		this.bookRepository = _bookRepository;
	}

	@Override
	public Book save(Book book) {
		if(bookRepository.existsByIsbn(book.getIsbn())) {
			throw new BusinessException(MSG_ERRO_ISBN_DUPLICADO);
		}
		return bookRepository.save(book);
	}

	@Override
	public Optional<Book> getById(Long idBook) {
		// TODO Auto-generated method stub
		return null;
	}

}
