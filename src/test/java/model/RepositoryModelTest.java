package model;

import model.enumfields.GenreType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryModelTest {
    private static Repository repo;

    // Setting test prep conditions

    @BeforeAll
    static void setUpTesting() {
        repo = new Repository();
    }

    @BeforeEach
    void setUp() {
        repo.setId(1L);
        repo.setName("Open Textbook Library");
        repo.setUrl("https://open.umn.edu/opentextbooks");
    }

    @AfterEach
    void tearDown() {
        repo.setId(0L);
        repo.setName(null);
        repo.setUrl(null);
        repo.getBooks().clear();
    }

    // Testing getters

    @Test
    void getId() {
        assertEquals(1, repo.getId());
    }

    @Test
    void getName() {
        assertNotNull(repo.getName());
    }

    @Test
    void getUrl() {
        assertNotNull(repo.getUrl());
    }

    @Test
    void getBooks() {
        assertEquals(0, repo.getBooks().size());
    }

    // Testing setters

    @Test
    void modifyName() {
        repo.setName("Kallipos");
        assertAll(
                () -> assertNotNull(repo.getName()),
                () -> assertEquals("Kallipos", repo.getName())
        );
    }

    @Test
    void modifyUrl() {
        repo.setUrl("https://repository.kallipos.gr/");
        assertAll(
                () -> assertNotNull(repo.getUrl()),
                () -> assertEquals("https://repository.kallipos.gr/", repo.getUrl())
        );
    }

    // Testing convenient methods
    @Test
    void addBook() {
        Book book = new Book();
        book.setTitle("A Tale of Two Cities");
        book.setGenre(GenreType.FICTION);
        book.setIsbn("978-0141439600");
        repo.getBooks().add(book);
        assertEquals(1, repo.getAllBooks().size());
    }
}