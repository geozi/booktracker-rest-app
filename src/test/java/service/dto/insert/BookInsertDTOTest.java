package service.dto.insert;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import model.enumfields.GenreType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class BookInsertDTOTest {
    private static BookInsertDTO bookInsertDTO;
    private static Validator validator;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        bookInsertDTO = new BookInsertDTO();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @BeforeEach
    void setUp() {
        bookInsertDTO.setTitle("A Tale of Two Cities");
        bookInsertDTO.setGenre(GenreType.FICTION);
        bookInsertDTO.setIsbn("978-0141439600");
    }

    @AfterEach
    void tearDown(){
        bookInsertDTO.setTitle(null);
        bookInsertDTO.setGenre(null);
        bookInsertDTO.setIsbn(null);
    }

    // Tests

    @Test
    void testValidValues(){

        // Assert
        assertAll(
                () -> assertNotNull(bookInsertDTO.getTitle()),
                () -> assertNotNull(bookInsertDTO.getGenre()),
                () -> assertNotNull(bookInsertDTO.getIsbn()),
                () -> assertTrue(validator.validate(bookInsertDTO).isEmpty()),
                () -> assertEquals(0, validator.validate(bookInsertDTO).size())
        );
    }

    @Test
    void testBelowMinValues() {
        // Act
        bookInsertDTO.setTitle("");
        bookInsertDTO.setIsbn("");

        // Assert
        assertAll(
                () -> assertNotNull(bookInsertDTO.getTitle()),
                () -> assertNotNull(bookInsertDTO.getIsbn()),
                () -> assertFalse(validator.validate(bookInsertDTO).isEmpty()),
                () -> assertEquals(2, validator.validate(bookInsertDTO).size())
        );
    }


    @Test
    void testNullValues() {
        // Act
        bookInsertDTO.setTitle(null);
        bookInsertDTO.setGenre(null);
        bookInsertDTO.setIsbn(null);

        // Assert
        assertAll(
                () -> assertNull(bookInsertDTO.getTitle()),
                () -> assertNull(bookInsertDTO.getGenre()),
                () -> assertNull(bookInsertDTO.getIsbn()),
                () -> assertFalse(validator.validate(bookInsertDTO).isEmpty()),
                () -> assertEquals(3, validator.validate(bookInsertDTO).size())
        );
    }

}