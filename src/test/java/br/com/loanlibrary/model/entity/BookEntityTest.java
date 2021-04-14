package br.com.loanlibrary.model.entity;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class BookEntityTest {

	@Test
	void gettersAndSettersToStringAndConstructor() {
		Book book = new Book();
		book.setDataCadastro(LocalDateTime.now());
		book.toString();
		
		Book book2 = Book.builder().build();
		book.toString();
		book.canEqual(book2);
		book.equals(book);
		book.hashCode();
		
	}

}
