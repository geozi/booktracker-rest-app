package service.dto.delete;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorDeleteDTOTest {
    private static AuthorDeleteDTO authorDeleteDTO;
    private static Validator validator;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        authorDeleteDTO = new AuthorDeleteDTO();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @BeforeEach
    void setUp(){
        authorDeleteDTO.setId(1L);
        authorDeleteDTO.setFirstname("Konstantinos");
        authorDeleteDTO.setLastname("Moustakas");
    }

    @AfterEach
    void tearDown() {
        authorDeleteDTO.setId(null);
        authorDeleteDTO.setFirstname(null);
        authorDeleteDTO.setLastname(null);
    }

    // Tests

    @Test
    void testValidValues(){

        // Assert
        assertAll(
                () -> assertNotNull(authorDeleteDTO.getId()),
                () -> assertNotNull(authorDeleteDTO.getFirstname()),
                () -> assertNotNull(authorDeleteDTO.getLastname()),
                () -> assertTrue(validator.validate(authorDeleteDTO).isEmpty()),
                () -> assertEquals(0, validator.validate(authorDeleteDTO).size())
        );
    }

    @Test
    void testBelowMinValues() {
        // Act
        authorDeleteDTO.setFirstname("");
        authorDeleteDTO.setLastname("");

        // Assert
        assertAll(
                () -> assertNotNull(authorDeleteDTO.getFirstname()),
                () -> assertNotNull(authorDeleteDTO.getLastname()),
                () -> assertFalse(validator.validate(authorDeleteDTO).isEmpty()),
                () -> assertEquals(2, validator.validate(authorDeleteDTO).size())
        );

    }

    @Test
    void testAboveMaxValues() {
        // Act
        authorDeleteDTO.setFirstname("%MVTiZz?Z1;;VAM94g7KFH,EKJCFeNhBxP}Q[S9dQ5JR[T");
        authorDeleteDTO.setLastname("%MVTiZz?Z1;;VAM94g7KFH,EKJCFeNhBxP}Q[S9dQ5JR[T");

        // Assert
        assertAll(
                () -> assertNotNull(authorDeleteDTO.getFirstname()),
                () -> assertNotNull(authorDeleteDTO.getLastname()),
                () -> assertFalse(validator.validate(authorDeleteDTO).isEmpty()),
                () -> assertEquals(2, validator.validate(authorDeleteDTO).size())
        );
    }

    @Test
    void testNullValues() {
        // Act
        authorDeleteDTO.setId(null);
        authorDeleteDTO.setFirstname(null);
        authorDeleteDTO.setLastname(null);

        // Assert
        assertAll(
                () -> assertNull(authorDeleteDTO.getId()),
                () -> assertNull(authorDeleteDTO.getFirstname()),
                () -> assertNull(authorDeleteDTO.getLastname()),
                () -> assertFalse(validator.validate(authorDeleteDTO).isEmpty()),
                () -> assertEquals(1, validator.validate(authorDeleteDTO).size())
        );

    }


}