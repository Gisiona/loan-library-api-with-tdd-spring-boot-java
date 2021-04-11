package br.com.loanlibrary.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.loanlibrary.model.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
