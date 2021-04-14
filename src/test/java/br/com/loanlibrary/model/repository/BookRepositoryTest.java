package br.com.loanlibrary.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.loanlibrary.model.entity.Book;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
class BookRepositoryTest {

	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	BookRepository bookRepository;
	
	@Test
	@DisplayName("Deve retornar TRUE quando consultar um livro ixistente para um ISBN.")
	void deveRetornarTrueQuandoExistirUmIsbnCadastradoParaUmLivro() {
		// cenario
		Book book = getBookEntity();
		
		// execucao
		book = entityManager.persist(book);
		
		boolean existsBook = bookRepository.existsByIsbn( book.getIsbn());
		
		// validacao
		assertThat(existsBook).isTrue();
	}
	
	@Test
	@DisplayName("Deve retornar FALSE quando consultar um livro inexistente para um ISBN.")
	void deveRetornarFalseQuandoNaoExistirUmIsbnCadastradoParaUmLivro() {
		// cenario
		String isbnBook = getBookEntity().getIsbn();
		
		// execucao		
		boolean existsBook = bookRepository.existsByIsbn(isbnBook);
		
		// validacao
		assertThat(existsBook).isFalse();
	}
	
	private Book getBookEntity() {		
		return Book.builder()
				.titulo("Aprenda programar em uma semana")
				.autor("Gisiona")
				.isbn("123456")
				.dataCadastro(LocalDateTime.now())
				.build();
	}

}
