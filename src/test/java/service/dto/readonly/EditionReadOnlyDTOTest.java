package service.dto.readonly;

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

class EditionReadOnlyDTOTest {
    private static EditionReadOnlyDTO editionReadOnlyDTO;
    private static Validator validator;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting(){
        editionReadOnlyDTO = new EditionReadOnlyDTO();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator= validatorFactory.getValidator();
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
        editionReadOnlyDTO.setId(1L);
        editionReadOnlyDTO.setFormat(FormatType.PAPERBACK);
        editionReadOnlyDTO.setLanguage(LanguageType.ENGLISH);
        editionReadOnlyDTO.setPageCount(489);
        editionReadOnlyDTO.setPublicationDate(date);
        editionReadOnlyDTO.setBook(book);
        editionReadOnlyDTO.setPublisher(pub);
    }

    @AfterEach
    void tearDown() {
        editionReadOnlyDTO.setId(null);
        editionReadOnlyDTO.setFormat(null);
        editionReadOnlyDTO.setLanguage(null);
        editionReadOnlyDTO.setPageCount(null);
        editionReadOnlyDTO.setPublicationDate(null);
        editionReadOnlyDTO.setBook(null);
        editionReadOnlyDTO.setPublisher(null);
    }

    // Tests

    @Test
    void testValidValues(){
        // Assert
        assertAll(
                () -> assertNotNull(editionReadOnlyDTO.getId()),
                () -> assertNotNull(editionReadOnlyDTO.getFormat()),
                () -> assertNotNull(editionReadOnlyDTO.getLanguage()),
                () -> assertNotNull(editionReadOnlyDTO.getPageCount()),
                () -> assertNotNull(editionReadOnlyDTO.getPublicationDate()),
                () -> assertNotNull(editionReadOnlyDTO.getBook()),
                () -> assertNotNull(editionReadOnlyDTO.getPublisher()),
                () -> assertTrue(validator.validate(editionReadOnlyDTO).isEmpty()),
                () -> assertEquals(0, validator.validate(editionReadOnlyDTO).size())
        );

    }

    @Test
    void testBelowMinValues() {
        // Act
        editionReadOnlyDTO.setPageCount(49);

        // Assert
        assertAll(
                () -> assertNotNull(editionReadOnlyDTO.getPageCount()),
                () -> assertFalse(validator.validate(editionReadOnlyDTO).isEmpty()),
                () -> assertEquals(1, validator.validate(editionReadOnlyDTO).size())
        );
    }

    @Test
    void testAboveMaxValues() {
        // Act
        editionReadOnlyDTO.setPageCount(2001);

        // Assert
        assertAll(
                () -> assertNotNull(editionReadOnlyDTO.getPageCount()),
                () -> assertFalse(validator.validate(editionReadOnlyDTO).isEmpty()),
                () -> assertEquals(1, validator.validate(editionReadOnlyDTO).size())
        );
    }

    @Test
    void testNullValues() {
        // Act
        editionReadOnlyDTO.setId(null);
        editionReadOnlyDTO.setFormat(null);
        editionReadOnlyDTO.setLanguage(null);
        editionReadOnlyDTO.setPageCount(null);
        editionReadOnlyDTO.setPublicationDate(null);
        editionReadOnlyDTO.setBook(null);
        editionReadOnlyDTO.setPublisher(null);

        // Assert
        assertAll(
                () -> assertNull(editionReadOnlyDTO.getId()),
                () -> assertNull(editionReadOnlyDTO.getFormat()),
                () -> assertNull(editionReadOnlyDTO.getLanguage()),
                () -> assertNull(editionReadOnlyDTO.getPageCount()),
                () -> assertNull(editionReadOnlyDTO.getPublicationDate()),
                () -> assertNull(editionReadOnlyDTO.getBook()),
                () -> assertNull(editionReadOnlyDTO.getPublisher()),
                () -> assertFalse(validator.validate(editionReadOnlyDTO).isEmpty()),
                () -> assertEquals(7, validator.validate(editionReadOnlyDTO).size())
        );
    }

}