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

class EditionModelTest {
    private static Edition edition;

    // Setting test prep conditions

    @BeforeAll
    static void setUpTesting() {
        edition = new Edition();
    }

    @BeforeEach
    void setUp() {

        Book book = new Book();
        book.setId(1L);
        book.setTitle("A Tale of Two Cities");
        book.setGenre(GenreType.FICTION);

        Publisher pub = new Publisher();
        pub.setId(1L);
        pub.setName("Penguin Books");
        pub.setPhoneNumber("+442070628940");
        pub.setEmail("consumerservices@penguinrandomhouse.com");
        pub.setStreetAddress("Embassy Gardens");
        pub.setCity("London");
        pub.setUrl("https://www.penguin.co.uk/");

        LocalDate date = LocalDate.parse("01-Jan-2003", DateTimeFormatter.ofPattern("d-MMM-yyyy"));
        edition.setId(1L);
        edition.setFormat(FormatType.PAPERBACK);
        edition.setLanguage(LanguageType.ENGLISH);
        edition.setPageCount(489);
        edition.setPublicationDate(date);
        edition.setBook(book);
        edition.setPublisher(pub);
    }

    @AfterEach
    void tearDown() {
        edition.setId(0L);
        edition.setFormat(null);
        edition.setLanguage(null);
        edition.setPageCount(0);
        edition.setPublicationDate(null);
        edition.setBook(null);
        edition.setPublisher(null);
    }

    // Testing getters

    @Test
    void getId() {
        assertEquals(1, edition.getId());
    }

    @Test
    void getFormat() {
        assertNotNull(edition.getFormat());
    }

    @Test
    void getLanguage() {
        assertNotNull(edition.getLanguage());
    }

    @Test
    void getPageCount() {
        assertEquals(489, edition.getPageCount());
    }

    @Test
    void getPublicationDate() {
        assertNotNull(edition.getPublicationDate());
    }

    @Test
    void getBook() {assertNotNull(edition.getBook());}

    @Test
    void getPublisher() {assertNotNull(edition.getPublisher());}

    // Testing setters

    @Test
    void modifyFormat() {
        edition.setFormat(FormatType.PAPERBACK);
        assertAll(
                () -> assertNotNull(edition.getFormat()),
                () -> assertEquals(FormatType.PAPERBACK, edition.getFormat())
        );
    }

    @Test
    void modifyLanguage() {
        edition.setLanguage(LanguageType.FRENCH);
        assertAll(
                () -> assertNotNull(edition.getLanguage()),
                () -> assertEquals(LanguageType.FRENCH,  edition.getLanguage())
        );
    }

    @Test
    void modifyPageCount() {
        edition.setPageCount(200);
        assertEquals(200, edition.getPageCount());
    }

    @Test
    void modifyPublicationDate() {
        LocalDate date = LocalDate.parse("07-Mar-2024", DateTimeFormatter.ofPattern("d-MMM-yyyy"));
        edition.setPublicationDate(date);
        assertAll(
                () -> assertNotNull(edition.getPublicationDate()),
                () -> assertEquals("2024-03-07", edition.getPublicationDate().toString())
        );
    }


}