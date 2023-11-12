package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.mapper.BookMapper;
import exercise.model.Author;
import exercise.model.Book;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> showAll(){
        return bookRepository.findAll().stream().map(bookMapper::map).toList();
    }

    public BookDTO findById(Long id){
        Book target = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        return bookMapper.map(target);
    }

    public BookDTO create(BookCreateDTO bookData){
        Long authorId = bookData.getAuthorId();
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ConstraintViolationException(Collections.emptySet()));
        Book target = bookMapper.map(bookData);
        target = bookRepository.save(target);
        return bookMapper.map(target);
    }

    public BookDTO update(Long id, BookUpdateDTO bookData){
        Long authorId = bookData.getAuthorId().get();
        if (authorId != null){
            Author author = authorRepository.findById(authorId)
                    .orElseThrow(() -> new ConstraintViolationException(Collections.emptySet()));
        }

        Book target = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        bookMapper.update(bookData, target);
        target = bookRepository.save(target);
        return bookMapper.map(target);
    }

    public void delete(Long id){
        bookRepository.deleteById(id);
    }

    // END

}
