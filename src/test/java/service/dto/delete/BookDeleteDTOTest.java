package service.dto.delete;


import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import model.enumfields.GenreType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookDeleteDTOTest {
    private static BookDeleteDTO bookDeleteDTO;
    private static Validator validator;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        bookDeleteDTO = new BookDeleteDTO();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @BeforeEach
    void setUp() {
        bookDeleteDTO.setId(1L);
        bookDeleteDTO.setTitle("The Three Musketeers");
        bookDeleteDTO.setGenre(GenreType.FICTION);
        bookDeleteDTO.setIsbn("978-1505234725");
    }

    @AfterEach
    void tearDown() {
        bookDeleteDTO.setId(null);
        bookDeleteDTO.setTitle(null);
        bookDeleteDTO.setGenre(null);
        bookDeleteDTO.setIsbn(null);
    }

    // Tests

    @Test
    void testValidValues(){
        // Assert
        assertAll(
                () -> assertNotNull(bookDeleteDTO.getId()),
                () -> assertNotNull(bookDeleteDTO.getTitle()),
                () -> assertNotNull(bookDeleteDTO.getGenre()),
                () -> assertNotNull(bookDeleteDTO.getIsbn()),
                () -> assertTrue(validator.validate(bookDeleteDTO).isEmpty()),
                () -> assertEquals(0, validator.validate(bookDeleteDTO).size())

        );
    }

    @Test
    void testBelowMinValues() {
        // Act
        bookDeleteDTO.setTitle("");
        bookDeleteDTO.setIsbn("");

        // Assert
        assertAll(
                () -> assertNotNull(bookDeleteDTO.getTitle()),
                () -> assertNotNull(bookDeleteDTO.getIsbn()),
                () -> assertFalse(validator.validate(bookDeleteDTO).isEmpty()),
                () -> assertEquals(2, validator.validate(bookDeleteDTO).size())
        );
    }

    @Test
    void testAboveMaxValues() {
        // Act
        bookDeleteDTO.setIsbn("978-150523472510");

        // Assert
        assertAll(
                () -> assertNotNull(bookDeleteDTO.getIsbn()),
                () -> assertFalse(validator.validate(bookDeleteDTO).isEmpty()),
                () -> assertEquals(1, validator.validate(bookDeleteDTO).size())
        );
    }

    @Test
    void testNullValues() {
        // Act
        bookDeleteDTO.setId(null);
        bookDeleteDTO.setTitle(null);
        bookDeleteDTO.setGenre(null);
        bookDeleteDTO.setIsbn(null);

        // Assert
        assertAll(
                () -> assertNull(bookDeleteDTO.getId()),
                () -> assertNull(bookDeleteDTO.getTitle()),
                () -> assertNull(bookDeleteDTO.getGenre()),
                () -> assertNull(bookDeleteDTO.getIsbn()),
                () -> assertFalse(validator.validate(bookDeleteDTO).isEmpty()),
                () -> assertEquals(1, validator.validate(bookDeleteDTO).size())
        );
    }

}