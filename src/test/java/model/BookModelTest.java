package model;

import model.enumfields.FormatType;
import model.enumfields.GenreType;
import model.enumfields.LanguageType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class BookModelTest {
    private static Book book;

    // Test prep conditions

    @BeforeAll
    static void setUpTesting() {
        book = new Book();
    }

    @BeforeEach
    void setUp() {
        book.setId(1L);
        book.setTitle("Romeo and Juliet");
        book.setGenre(GenreType.DRAMA);
        book.setIsbn("978-0743477116");
    }

    @AfterEach
    void tearDown() {
        book.setId(0L);
        book.setTitle(null);
        book.setGenre(null);
        book.setIsbn(null);
        book.getEditions().clear();
        book.getAuthors().clear();
        book.getRepos().clear();
    }

    // Testing getters

    @Test
    void getId() {
        assertEquals(1, book.getId());
    }

    @Test
    void getTitle() {
        assertNotNull(book.getTitle());
    }

    @Test
    void getISBN() {
        assertNotNull(book.getIsbn());
    }

    @Test
    void getEditions() {
        assertEquals(0, book.getEditions().size());
    }

    @Test
    void getAuthors() {
        assertEquals(0, book.getAuthors().size());
    }

    @Test
    void getRepos() {
        assertEquals(0, book.getRepos().size());
    }

    // Testing setters

    @Test
    void modifyTitle() {
        book.setTitle("The Hobbit");
        assertAll(
                () -> assertNotNull(book.getTitle()),
                () -> assertEquals("The Hobbit", book.getTitle())
        );
    }

    @Test
    void modifyGenre() {
        book.setGenre(GenreType.FICTION);
        assertAll(
                () -> assertNotNull(book.getGenre()),
                () -> assertEquals(GenreType.FICTION, book.getGenre())
        );
    }

    @Test
    void modifyISBN() {
        book.setIsbn("978-0345339683");
        assertAll(
                () -> assertNotNull(book.getIsbn()),
                () -> assertEquals("978-0345339683", book.getIsbn())
        ) ;
    }

    // Testing convenient methods

    @Test
    void addAuthor() {
        Author author = new Author();
        author.setFirstname("Jules");
        author.setLastname("Vernes");
        book.addAuthor(author);
        assertEquals(1,  book.getAllAuthors().size());
    }

    @Test
    void addEdition() {
        LocalDate date = LocalDate.parse("10-Feb-2024", DateTimeFormatter.ofPattern("d-MMM-yyyy"));
        Edition edition = new Edition();
        edition.setFormat(FormatType.EBOOK);
        edition.setPublicationDate(date);
        edition.setPageCount(300);
        edition.setLanguage(LanguageType.ENGLISH);
        book.addEdition(edition);
        assertEquals(1, book.getAllEditions().size());
    }

    @Test
    void addRepository() {
        Repository repo = new Repository();
        repo.setName("Oapen");
        repo.setUrl("https://oapen.org/");
        book.addRepository(repo);
        assertEquals(1, book.getAllRepositories().size());
    }
}