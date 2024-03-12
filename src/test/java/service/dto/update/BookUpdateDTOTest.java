package service.dto.update;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import model.enumfields.GenreType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookUpdateDTOTest {
    private static BookUpdateDTO bookUpdateDTO;
    private static Validator validator;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        bookUpdateDTO = new BookUpdateDTO();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @BeforeEach
    void setUp() {
        bookUpdateDTO.setId(1L);
        bookUpdateDTO.setTitle("The Three Musketeers");
        bookUpdateDTO.setGenre(GenreType.FICTION);
        bookUpdateDTO.setIsbn("978-1505234725");
    }

    @AfterEach
    void tearDown() {
        bookUpdateDTO.setId(null);
        bookUpdateDTO.setTitle(null);
        bookUpdateDTO.setGenre(null);
        bookUpdateDTO.setIsbn(null);
    }

    // Tests

    @Test
    void testValidValues(){
        // Assert
        assertAll(
                () -> assertNotNull(bookUpdateDTO.getId()),
                () -> assertNotNull(bookUpdateDTO.getTitle()),
                () -> assertNotNull(bookUpdateDTO.getGenre()),
                () -> assertNotNull(bookUpdateDTO.getIsbn()),
                () -> assertTrue(validator.validate(bookUpdateDTO).isEmpty()),
                () -> assertEquals(0, validator.validate(bookUpdateDTO).size())

        );
    }

    @Test
    void testBelowMinValues() {
        // Act
        bookUpdateDTO.setTitle("");
        bookUpdateDTO.setIsbn("");

        // Assert
        assertAll(
                () -> assertNotNull(bookUpdateDTO.getTitle()),
                () -> assertNotNull(bookUpdateDTO.getIsbn()),
                () -> assertFalse(validator.validate(bookUpdateDTO).isEmpty()),
                () -> assertEquals(2, validator.validate(bookUpdateDTO).size())
        );
    }

    @Test
    void testAboveMaxValues() {
        // Act
        bookUpdateDTO.setIsbn("978-150523472510");

        // Assert
        assertAll(
                () -> assertNotNull(bookUpdateDTO.getIsbn()),
                () -> assertFalse(validator.validate(bookUpdateDTO).isEmpty()),
                () -> assertEquals(1, validator.validate(bookUpdateDTO).size())
        );
    }

    @Test
    void testNullValues() {
        // Act
        bookUpdateDTO.setId(null);
        bookUpdateDTO.setTitle(null);
        bookUpdateDTO.setGenre(null);
        bookUpdateDTO.setIsbn(null);

        // Assert
        assertAll(
                () -> assertNull(bookUpdateDTO.getId()),
                () -> assertNull(bookUpdateDTO.getTitle()),
                () -> assertNull(bookUpdateDTO.getGenre()),
                () -> assertNull(bookUpdateDTO.getIsbn()),
                () -> assertFalse(validator.validate(bookUpdateDTO).isEmpty()),
                () -> assertEquals(4, validator.validate(bookUpdateDTO).size())
        );
    }

}