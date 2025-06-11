package com.fahllivro.backend.service;

import com.fahllivro.backend.model.Book;
import com.fahllivro.backend.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // C - Criar livro
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    // R - Buscar por ID
    public Optional<Book> getById(Long id) {
        return bookRepository.findById(id);
    }

    // R - Buscar todos os livros
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    // R - Buscar por título
    public List<Book> getByTitulo(String titulo) {
        return bookRepository.findByTituloContainingIgnoreCase(titulo);
    }

    // R - Buscar por autor
    public List<Book> getByAutor(String autor) {
        return bookRepository.findByAutorContainingIgnoreCase(autor);
    }

    // R - Buscar por título e autor
    public List<Book> getByTituloAndAutor(String titulo, String autor) {
        return bookRepository.findByTituloContainingIgnoreCaseAndAutorContainingIgnoreCase(titulo, autor);
    }

    // R - Buscar por ID do usuário
    public List<Book> getByUserId(Long userId) {
        return bookRepository.findByUserId(userId);
    }

    // U - Atualizar livro
    public Book update(Long id, Book updatedBook) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        existing.setTitulo(updatedBook.getTitulo());
        existing.setAutor(updatedBook.getAutor());
        existing.setDescricao(updatedBook.getDescricao());
        existing.setQuantidade(updatedBook.getQuantidade());
        existing.setUser(updatedBook.getUser()); // pode ser mantido se quiser reatribuir

        return bookRepository.save(existing);
    }

    // D - Deletar livro
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
