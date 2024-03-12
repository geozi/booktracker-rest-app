package service.dto.insert;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorInsertDTOTest {
    private static AuthorInsertDTO authorInsertDTO;
    private static Validator validator;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        authorInsertDTO = new AuthorInsertDTO();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @BeforeEach
    void setUp() {
        authorInsertDTO.setFirstname("Jamal");
        authorInsertDTO.setLastname("Wilcox");
    }

    @AfterEach
    void tearDown() {
        authorInsertDTO.setFirstname(null);
        authorInsertDTO.setLastname(null);
    }

    // Tests

    @Test
    void testValidValues() {
        // Assert
        assertAll(
                () -> assertNotNull(authorInsertDTO.getFirstname()),
                () -> assertNotNull(authorInsertDTO.getLastname()),
                () -> assertTrue(validator.validate(authorInsertDTO).isEmpty()),
                () -> assertEquals(0, validator.validate(authorInsertDTO).size())
        );
    }

    @Test
    void testBelowMinValues() {
        // Act
        authorInsertDTO.setFirstname("");
        authorInsertDTO.setLastname("");

        // Assert
        assertAll(
                () -> assertNotNull(authorInsertDTO.getFirstname()),
                () -> assertNotNull(authorInsertDTO.getLastname()),
                () -> assertFalse(validator.validate(authorInsertDTO).isEmpty()),
                () -> assertEquals(2, validator.validate(authorInsertDTO).size())
        );
    }

    @Test
    void testAboveMaxValues() {
        // Act
        authorInsertDTO.setFirstname("brnvsbkeqbpnvomhpfafytvmkruueiussfdlchskppwuukulnfcwfxilptzlt");
        authorInsertDTO.setLastname("xcfzxeikazxzqiohtvgoftuiwrenvggnusbeuxzzpjbaibpbzd");

        // Assert
        assertAll(
                () -> assertNotNull(authorInsertDTO.getFirstname()),
                () -> assertNotNull(authorInsertDTO.getLastname()),
                () -> assertFalse(validator.validate(authorInsertDTO).isEmpty()),
                () -> assertEquals(2, validator.validate(authorInsertDTO).size())
        );

    }

    @Test
    void testNullValues() {
        // Act
        authorInsertDTO.setFirstname(null);
        authorInsertDTO.setLastname(null);

        // Assert
        assertAll(
                () -> assertNull(authorInsertDTO.getFirstname()),
                () -> assertNull(authorInsertDTO.getLastname()),
                () -> assertFalse(validator.validate(authorInsertDTO).isEmpty()),
                () -> assertEquals(2, validator.validate(authorInsertDTO).size())
        );

    }
}