package service.dto.readonly;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorReadOnlyDTOTest {
    private static AuthorReadOnlyDTO authorReadOnlyDTO;
    private static Validator validator;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        authorReadOnlyDTO = new AuthorReadOnlyDTO();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @BeforeEach
    void setUp(){
        authorReadOnlyDTO.setId(1L);
        authorReadOnlyDTO.setFirstname("Konstantinos");
        authorReadOnlyDTO.setLastname("Moustakas");
    }

    @AfterEach
    void tearDown() {
        authorReadOnlyDTO.setId(null);
        authorReadOnlyDTO.setFirstname(null);
        authorReadOnlyDTO.setLastname(null);
    }

    // Tests

    @Test
    void testValidValues(){

        // Assert
        assertAll(
                () -> assertNotNull(authorReadOnlyDTO.getId()),
                () -> assertNotNull(authorReadOnlyDTO.getFirstname()),
                () -> assertNotNull(authorReadOnlyDTO.getLastname()),
                () -> assertTrue(validator.validate(authorReadOnlyDTO).isEmpty()),
                () -> assertEquals(0, validator.validate(authorReadOnlyDTO).size())
        );
    }

    @Test
    void testBelowMinValues() {
        // Act
        authorReadOnlyDTO.setFirstname("");
        authorReadOnlyDTO.setLastname("");

        // Assert
        assertAll(
                () -> assertNotNull(authorReadOnlyDTO.getFirstname()),
                () -> assertNotNull(authorReadOnlyDTO.getLastname()),
                () -> assertFalse(validator.validate(authorReadOnlyDTO).isEmpty()),
                () -> assertEquals(2, validator.validate(authorReadOnlyDTO).size())
        );

    }

    @Test
    void testAboveMaxValues() {
        // Act
        authorReadOnlyDTO.setFirstname("%MVTiZz?Z1;;VAM94g7KFH,EKJCFeNhBxP}Q[S9dQ5JR[T");
        authorReadOnlyDTO.setLastname("%MVTiZz?Z1;;VAM94g7KFH,EKJCFeNhBxP}Q[S9dQ5JR[T");

        // Assert
        assertAll(
                () -> assertNotNull(authorReadOnlyDTO.getFirstname()),
                () -> assertNotNull(authorReadOnlyDTO.getLastname()),
                () -> assertFalse(validator.validate(authorReadOnlyDTO).isEmpty()),
                () -> assertEquals(2, validator.validate(authorReadOnlyDTO).size())
        );
    }

    @Test
    void testNullValues() {
        // Act
        authorReadOnlyDTO.setId(null);
        authorReadOnlyDTO.setFirstname(null);
        authorReadOnlyDTO.setLastname(null);

        // Assert
        assertAll(
                () -> assertNull(authorReadOnlyDTO.getId()),
                () -> assertNull(authorReadOnlyDTO.getFirstname()),
                () -> assertNull(authorReadOnlyDTO.getLastname()),
                () -> assertFalse(validator.validate(authorReadOnlyDTO).isEmpty()),
                () -> assertEquals(3, validator.validate(authorReadOnlyDTO).size())
        );

    }

}