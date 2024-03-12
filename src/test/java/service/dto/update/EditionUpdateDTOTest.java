package service.dto.update;

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

class EditionUpdateDTOTest {
    private static EditionUpdateDTO editionUpdateDTO;
    private static Validator validator;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting(){
        editionUpdateDTO = new EditionUpdateDTO();
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
        editionUpdateDTO.setId(1L);
        editionUpdateDTO.setFormat(FormatType.PAPERBACK);
        editionUpdateDTO.setLanguage(LanguageType.ENGLISH);
        editionUpdateDTO.setPageCount(489);
        editionUpdateDTO.setPublicationDate(date);
        editionUpdateDTO.setBook(book);
        editionUpdateDTO.setPublisher(pub);
    }

    @AfterEach
    void tearDown() {
        editionUpdateDTO.setId(null);
        editionUpdateDTO.setFormat(null);
        editionUpdateDTO.setLanguage(null);
        editionUpdateDTO.setPageCount(null);
        editionUpdateDTO.setPublicationDate(null);
        editionUpdateDTO.setBook(null);
        editionUpdateDTO.setPublisher(null);
    }

    // Tests

    @Test
    void testValidValues(){
        // Assert
        assertAll(
                () -> assertNotNull(editionUpdateDTO.getId()),
                () -> assertNotNull(editionUpdateDTO.getFormat()),
                () -> assertNotNull(editionUpdateDTO.getLanguage()),
                () -> assertNotNull(editionUpdateDTO.getPageCount()),
                () -> assertNotNull(editionUpdateDTO.getPublicationDate()),
                () -> assertNotNull(editionUpdateDTO.getBook()),
                () -> assertNotNull(editionUpdateDTO.getPublisher()),
                () -> assertTrue(validator.validate(editionUpdateDTO).isEmpty()),
                () -> assertEquals(0, validator.validate(editionUpdateDTO).size())
        );

    }

    @Test
    void testBelowMinValues() {
        // Act
        editionUpdateDTO.setPageCount(49);

        // Assert
        assertAll(
                () -> assertNotNull(editionUpdateDTO.getPageCount()),
                () -> assertFalse(validator.validate(editionUpdateDTO).isEmpty()),
                () -> assertEquals(1, validator.validate(editionUpdateDTO).size())
        );
    }

    @Test
    void testAboveMaxValues() {
        // Act
        editionUpdateDTO.setPageCount(2001);

        // Assert
        assertAll(
                () -> assertNotNull(editionUpdateDTO.getPageCount()),
                () -> assertFalse(validator.validate(editionUpdateDTO).isEmpty()),
                () -> assertEquals(1, validator.validate(editionUpdateDTO).size())
        );
    }

    @Test
    void testNullValues() {
        // Act
        editionUpdateDTO.setId(null);
        editionUpdateDTO.setFormat(null);
        editionUpdateDTO.setLanguage(null);
        editionUpdateDTO.setPageCount(null);
        editionUpdateDTO.setPublicationDate(null);
        editionUpdateDTO.setBook(null);
        editionUpdateDTO.setPublisher(null);

        // Assert
        assertAll(
                () -> assertNull(editionUpdateDTO.getId()),
                () -> assertNull(editionUpdateDTO.getFormat()),
                () -> assertNull(editionUpdateDTO.getLanguage()),
                () -> assertNull(editionUpdateDTO.getPageCount()),
                () -> assertNull(editionUpdateDTO.getPublicationDate()),
                () -> assertNull(editionUpdateDTO.getBook()),
                () -> assertNull(editionUpdateDTO.getPublisher()),
                () -> assertFalse(validator.validate(editionUpdateDTO).isEmpty()),
                () -> assertEquals(7, validator.validate(editionUpdateDTO).size())
        );
    }

}