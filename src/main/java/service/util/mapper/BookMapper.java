package service.util.mapper;

import model.Book;
import service.dto.delete.BookDeleteDTO;
import service.dto.insert.BookInsertDTO;
import service.dto.readonly.BookReadOnlyDTO;
import service.dto.update.BookUpdateDTO;

/**
 * The {@link BookMapper} class is a utility class that maps DTOs to
 * the Book model class.
 */
public class BookMapper {

    private BookMapper() {}

    public static Book mapToBook(BookInsertDTO dto) {
        return new Book(dto.getTitle(), dto.getGenre(), dto.getIsbn());
    }

    public static Book mapToBook(BookUpdateDTO dto) {
        return new Book(dto.getId(), dto.getTitle(), dto.getGenre(), dto.getIsbn());
    }
    public static Book mapToBook(BookDeleteDTO dto) {
        return new Book(dto.getId(), dto.getTitle(), dto.getGenre(), dto.getIsbn());
    }
    public static BookReadOnlyDTO mapToReadOnlyDTO(Book book) {
        return new BookReadOnlyDTO(book.getId(), book.getTitle(), book.getGenre(), book.getIsbn());
    }
}
