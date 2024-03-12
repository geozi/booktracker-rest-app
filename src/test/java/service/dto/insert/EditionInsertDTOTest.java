package service.dto.insert;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import model.Book;
import model.Publisher;
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

class EditionInsertDTOTest {
    private static EditionInsertDTO editionInsertDTO;
    private static Validator validator;

    // Setting up prep conditions

    @BeforeAll
    static void setUpTesting() {
        editionInsertDTO = new EditionInsertDTO();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
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
        editionInsertDTO.setFormat(FormatType.PAPERBACK);
        editionInsertDTO.setLanguage(LanguageType.ENGLISH);
        editionInsertDTO.setPageCount(489);
        editionInsertDTO.setPublicationDate(date);
        editionInsertDTO.setBook(book);
        editionInsertDTO.setPublisher(pub);
    }

    @AfterEach
    void tearDown() {
        editionInsertDTO.setFormat(null);
        editionInsertDTO.setLanguage(null);
        editionInsertDTO.setPageCount(null);
        editionInsertDTO.setPublicationDate(null);
        editionInsertDTO.setBook(null);
        editionInsertDTO.setPublisher(null);
    }

    // Tests

    @Test
    void testValidValues(){
        // Assert
        assertAll(
                () -> assertNotNull(editionInsertDTO.getFormat()),
                () -> assertNotNull(editionInsertDTO.getLanguage()),
                () -> assertNotNull(editionInsertDTO.getPageCount()),
                () -> assertNotNull(editionInsertDTO.getPublicationDate()),
                () -> assertNotNull(editionInsertDTO.getBook()),
                () -> assertNotNull(editionInsertDTO.getPublisher()),
                () -> assertTrue(validator.validate(editionInsertDTO).isEmpty()),
                () -> assertEquals(0, validator.validate(editionInsertDTO).size())
        );
    }

    @Test
    void testBelowMinValues() {
        // Act
        editionInsertDTO.setPageCount(49);

        // Assert
        assertAll(
                () -> assertNotNull(editionInsertDTO.getPageCount()),
                () -> assertFalse(validator.validate(editionInsertDTO).isEmpty()),
                () -> assertEquals(1, validator.validate(editionInsertDTO).size())
        );
    }

    @Test
    void testAboveMaxValues() {
        // Act
        editionInsertDTO.setPageCount(2001);

        // Assert
        assertAll(
                () -> assertNotNull(editionInsertDTO.getPageCount()),
                () -> assertFalse(validator.validate(editionInsertDTO).isEmpty()),
                () -> assertEquals(1, validator.validate(editionInsertDTO).size())
        );

    }

    @Test
    void testNullValues() {
        editionInsertDTO.setFormat(null);
        editionInsertDTO.setLanguage(null);
        editionInsertDTO.setPageCount(null);
        editionInsertDTO.setPublicationDate(null);
        editionInsertDTO.setBook(null);
        editionInsertDTO.setPublisher(null);

        assertAll(
                () -> assertNull(editionInsertDTO.getFormat()),
                () -> assertNull(editionInsertDTO.getLanguage()),
                () -> assertNull(editionInsertDTO.getPageCount()),
                () -> assertNull(editionInsertDTO.getPublicationDate()),
                () -> assertNull(editionInsertDTO.getBook()),
                () -> assertNull(editionInsertDTO.getPublisher()),
                () -> assertFalse(validator.validate(editionInsertDTO).isEmpty()),
                () -> assertEquals(6, validator.validate(editionInsertDTO).size())
        );
    }

}