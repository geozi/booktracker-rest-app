package service;

import dao.util.DatabaseHelper;
import dao.util.JPAHelper;
import model.Book;
import model.enumfields.GenreType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.dto.delete.BookDeleteDTO;
import service.dto.insert.BookInsertDTO;
import service.dto.update.BookUpdateDTO;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    private static IBookService bookService;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        bookService = new BookServiceImpl();
    }

    @BeforeEach
    void setUp() throws Exception {
        createdDummyData();
    };

    @AfterEach
    void tearDown() {
        JPAHelper.getEntityManager().clear();
        DatabaseHelper.eraseData();
    };

    // Tests

    @Test
    void insertBook() throws Exception{
        // Arrange
        BookInsertDTO b3 = new BookInsertDTO();
        b3.setTitle("The Three Musketeers");
        b3.setGenre(GenreType.FICTION);
        b3.setIsbn("978-1505234725");

        // Act
        Book insertedBook = bookService.insertBook(b3);

        // Assert
        assertAll(
                () -> assertNotNull(insertedBook),
                () -> assertEquals("The Three Musketeers", insertedBook.getTitle())
        );
    }

    @Test
    void updateBook() throws Exception{
        // Arrange
        BookUpdateDTO bookToUpdate = new BookUpdateDTO();
        bookToUpdate.setId(1L);
        bookToUpdate.setTitle("The Alchemist");
        bookToUpdate.setGenre(GenreType.FICTION);
        bookToUpdate.setIsbn("978-0062315007");

        // Act
        Book updatedBook = bookService.updateBook(bookToUpdate);

        // Assert
        assertAll(
                () -> assertNotNull(updatedBook),
                () -> assertNotEquals("Memoirs of a Geisha", updatedBook.getTitle()),
                () -> assertEquals("The Alchemist", updatedBook.getTitle())
        );
    }

    @Test
    void deleteBook() throws Exception{
        // Arrange
        BookDeleteDTO bookToDelete = new BookDeleteDTO();
        bookToDelete.setId(1L);

        // Act
        bookService.deleteBook(bookToDelete);

        // Assert
        assertAll(
                () -> assertEquals(1, bookService.getAllBooks().size())
        );

    }

    @Test
    void getBookById() throws Exception{
        // Act
        Book book = bookService.getBookById(2L);

        // Assert
        assertAll(
                () -> assertNotNull(book),
                () -> assertEquals("Shogun", book.getTitle())
        );
    }

    public static void createdDummyData() throws Exception {
        BookInsertDTO b1 = new BookInsertDTO();
        b1.setTitle("Memoirs of a Geisha");
        b1.setGenre(GenreType.FICTION);
        b1.setIsbn("978-1400096893");

        bookService.insertBook(b1);

        BookInsertDTO b2 = new BookInsertDTO();
        b2.setTitle("Shogun");
        b2.setGenre(GenreType.FICTION);
        b2.setIsbn("978-1982603847");

        bookService.insertBook(b2);

    };

}