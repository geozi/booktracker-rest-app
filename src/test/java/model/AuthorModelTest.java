package model;

import model.enumfields.GenreType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorModelTest {
    private static Author author;

    // Setting test prep conditions

    @BeforeAll
    static void setUpTesting() {
        author = new Author();
    }

    @BeforeEach
    void setUp() {
        author.setId(1L);
        author.setFirstname("Arthur");
        author.setLastname("Golden");
    }

    @AfterEach
    void tearDown() {
        author.setId(0L);
        author.setFirstname(null);
        author.setLastname(null);
        author.getBooks().clear();
    }

    // Testing getters

    @Test
    void getId() {
        assertEquals(1, author.getId());
    }

    @Test
    void getFirstname() {
        assertNotNull(author.getFirstname());
    }

    @Test
    void getLastname() {
        assertNotNull(author.getLastname());
    }

    @Test
    void getBooks() {
        assertEquals(0, author.getBooks().size());
    }

    // Testing setters

    @Test
    void modifyFirstname() {
        author.setFirstname("Margaret");
        assertAll(
                () -> assertNotNull(author.getFirstname()),
                () -> assertEquals("Margaret", author.getFirstname())
        );
    }

    @Test
    void modifyLastname() {
        author.setLastname("Mitchell");
        assertAll(
                () -> assertNotNull(author.getLastname()),
                () -> assertEquals("Mitchell", author.getLastname())
        );
    }

    // Testing convenient methods

    @Test
    void addBook() {
        Book book = new Book();
        book.setTitle("A Tale of Two Cities");
        book.setGenre(GenreType.FICTION);
        book.setIsbn("978-0141439600");
        author.getBooks().add(book);
        assertEquals(1, author.getAllBooks().size());
    }

}