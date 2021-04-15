package br.com.loanlibrary.service;

import java.util.Optional;

import br.com.loanlibrary.model.entity.Book;

public interface BookService {
	Book save(Book book);

	Optional<Book> getById(Long idBook);
}
