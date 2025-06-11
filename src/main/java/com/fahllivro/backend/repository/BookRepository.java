package com.fahllivro.backend.repository;

import com.fahllivro.backend.model.Book;
import com.fahllivro.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTituloContainingIgnoreCase(String titulo);

    List<Book> findByAutorContainingIgnoreCase(String autor);

    List<Book> findByTituloContainingIgnoreCaseAndAutorContainingIgnoreCase(String titulo, String autor);

    List<Book> findByUserId(Long userId);
}
