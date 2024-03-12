package service.dto.readonly;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import model.enumfields.GenreType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookReadOnlyDTOTest {
    private static BookReadOnlyDTO bookReadOnlyDTO;
    private static Validator validator;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        bookReadOnlyDTO = new BookReadOnlyDTO();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @BeforeEach
    void setUp() {
        bookReadOnlyDTO.setId(1L);
        bookReadOnlyDTO.setTitle("The Three Musketeers");
        bookReadOnlyDTO.setGenre(GenreType.FICTION);
        bookReadOnlyDTO.setIsbn("978-1505234725");
    }

    @AfterEach
    void tearDown() {
        bookReadOnlyDTO.setId(null);
        bookReadOnlyDTO.setTitle(null);
        bookReadOnlyDTO.setGenre(null);
        bookReadOnlyDTO.setIsbn(null);
    }

    // Tests

    @Test
    void testValidValues(){
        // Assert
        assertAll(
                () -> assertNotNull(bookReadOnlyDTO.getId()),
                () -> assertNotNull(bookReadOnlyDTO.getTitle()),
                () -> assertNotNull(bookReadOnlyDTO.getGenre()),
                () -> assertNotNull(bookReadOnlyDTO.getIsbn()),
                () -> assertTrue(validator.validate(bookReadOnlyDTO).isEmpty()),
                () -> assertEquals(0, validator.validate(bookReadOnlyDTO).size())

        );
    }

    @Test
    void testBelowMinValues() {
        // Act
        bookReadOnlyDTO.setTitle("");
        bookReadOnlyDTO.setIsbn("");

        // Assert
        assertAll(
                () -> assertNotNull(bookReadOnlyDTO.getTitle()),
                () -> assertNotNull(bookReadOnlyDTO.getIsbn()),
                () -> assertFalse(validator.validate(bookReadOnlyDTO).isEmpty()),
                () -> assertEquals(2, validator.validate(bookReadOnlyDTO).size())
        );
    }

    @Test
    void testAboveMaxValues() {
        // Act
        bookReadOnlyDTO.setIsbn("978-150523472510");

        // Assert
        assertAll(
                () -> assertNotNull(bookReadOnlyDTO.getIsbn()),
                () -> assertFalse(validator.validate(bookReadOnlyDTO).isEmpty()),
                () -> assertEquals(1, validator.validate(bookReadOnlyDTO).size())
        );
    }

    @Test
    void testNullValues() {
        // Act
        bookReadOnlyDTO.setId(null);
        bookReadOnlyDTO.setTitle(null);
        bookReadOnlyDTO.setGenre(null);
        bookReadOnlyDTO.setIsbn(null);

        // Assert
        assertAll(
                () -> assertNull(bookReadOnlyDTO.getId()),
                () -> assertNull(bookReadOnlyDTO.getTitle()),
                () -> assertNull(bookReadOnlyDTO.getGenre()),
                () -> assertNull(bookReadOnlyDTO.getIsbn()),
                () -> assertFalse(validator.validate(bookReadOnlyDTO).isEmpty()),
                () -> assertEquals(4, validator.validate(bookReadOnlyDTO).size())
        );
    }

}