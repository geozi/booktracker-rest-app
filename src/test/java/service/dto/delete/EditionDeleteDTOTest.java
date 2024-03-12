package service.dto.delete;

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

class EditionDeleteDTOTest {
    private static EditionDeleteDTO editionDeleteDTO;
    private static Validator validator;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting(){
        editionDeleteDTO = new EditionDeleteDTO();
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
        editionDeleteDTO.setId(1L);
        editionDeleteDTO.setFormat(FormatType.PAPERBACK);
        editionDeleteDTO.setLanguage(LanguageType.ENGLISH);
        editionDeleteDTO.setPageCount(489);
        editionDeleteDTO.setPublicationDate(date);
        editionDeleteDTO.setBook(book);
        editionDeleteDTO.setPublisher(pub);
    }

    @AfterEach
    void tearDown() {
        editionDeleteDTO.setId(null);
        editionDeleteDTO.setFormat(null);
        editionDeleteDTO.setLanguage(null);
        editionDeleteDTO.setPageCount(null);
        editionDeleteDTO.setPublicationDate(null);
        editionDeleteDTO.setBook(null);
        editionDeleteDTO.setPublisher(null);
    }

    // Tests

    @Test
    void testValidValues(){
        // Assert
        assertAll(
                () -> assertNotNull(editionDeleteDTO.getId()),
                () -> assertNotNull(editionDeleteDTO.getFormat()),
                () -> assertNotNull(editionDeleteDTO.getLanguage()),
                () -> assertNotNull(editionDeleteDTO.getPageCount()),
                () -> assertNotNull(editionDeleteDTO.getPublicationDate()),
                () -> assertNotNull(editionDeleteDTO.getBook()),
                () -> assertNotNull(editionDeleteDTO.getPublisher()),
                () -> assertTrue(validator.validate(editionDeleteDTO).isEmpty()),
                () -> assertEquals(0, validator.validate(editionDeleteDTO).size())
        );

    }

    @Test
    void testBelowMinValues() {
        // Act
        editionDeleteDTO.setPageCount(49);

        // Assert
        assertAll(
                () -> assertNotNull(editionDeleteDTO.getPageCount()),
                () -> assertFalse(validator.validate(editionDeleteDTO).isEmpty()),
                () -> assertEquals(1, validator.validate(editionDeleteDTO).size())
        );
    }

    @Test
    void testAboveMaxValues() {
        // Act
        editionDeleteDTO.setPageCount(2001);

        // Assert
        assertAll(
                () -> assertNotNull(editionDeleteDTO.getPageCount()),
                () -> assertFalse(validator.validate(editionDeleteDTO).isEmpty()),
                () -> assertEquals(1, validator.validate(editionDeleteDTO).size())
        );
    }

    @Test
    void testNullValues() {
        // Act
        editionDeleteDTO.setId(null);
        editionDeleteDTO.setFormat(null);
        editionDeleteDTO.setLanguage(null);
        editionDeleteDTO.setPageCount(null);
        editionDeleteDTO.setPublicationDate(null);
        editionDeleteDTO.setBook(null);
        editionDeleteDTO.setPublisher(null);

        // Assert
        assertAll(
                () -> assertNull(editionDeleteDTO.getId()),
                () -> assertNull(editionDeleteDTO.getFormat()),
                () -> assertNull(editionDeleteDTO.getLanguage()),
                () -> assertNull(editionDeleteDTO.getPageCount()),
                () -> assertNull(editionDeleteDTO.getPublicationDate()),
                () -> assertNull(editionDeleteDTO.getBook()),
                () -> assertNull(editionDeleteDTO.getPublisher()),
                () -> assertFalse(validator.validate(editionDeleteDTO).isEmpty()),
                () -> assertEquals(1, validator.validate(editionDeleteDTO).size())
        );
    }

}