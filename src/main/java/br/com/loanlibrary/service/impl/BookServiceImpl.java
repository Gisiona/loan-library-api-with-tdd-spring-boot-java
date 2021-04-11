package br.com.loanlibrary.service.impl;

import org.springframework.stereotype.Service;

import br.com.loanlibrary.model.entity.Book;
import br.com.loanlibrary.model.repository.BookRepository;
import br.com.loanlibrary.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private BookRepository bookRepository;
	
	public BookServiceImpl(BookRepository _bookRepository) {
		this.bookRepository = _bookRepository;
	}

	@Override
	public Book save(Book book) {		
		return bookRepository.save(book);
	}

}
