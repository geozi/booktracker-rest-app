package service;

import model.Book;
import service.dto.delete.BookDeleteDTO;
import service.dto.insert.BookInsertDTO;
import service.dto.update.BookUpdateDTO;
import service.exceptions.EntityNotFoundException;

import java.util.List;

/**
 * The {@link IBookService} interface contains the
 * Service-layer definitions of the CRUD wrapper methods
 * for the Book primary entity.
 * It inherits from the {@link IGenericService}
 */
public interface IBookService extends IGenericService {

    Book insertBook(BookInsertDTO dto) throws Exception;

    Book updateBook(BookUpdateDTO dto) throws EntityNotFoundException;

    void deleteBook(BookDeleteDTO dto) throws EntityNotFoundException;

    List<Book> getBooksByTitle(String title) throws EntityNotFoundException;

    List<Book> getAllBooks() throws Exception;

    Book getBookById(Long id) throws EntityNotFoundException;
}
