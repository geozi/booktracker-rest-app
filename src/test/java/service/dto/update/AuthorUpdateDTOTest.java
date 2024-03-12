package service.dto.update;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorUpdateDTOTest {
    private static AuthorUpdateDTO authorUpdateDTO;
    private static Validator validator;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        authorUpdateDTO = new AuthorUpdateDTO();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @BeforeEach
    void setUp(){
        authorUpdateDTO.setId(1L);
        authorUpdateDTO.setFirstname("Konstantinos");
        authorUpdateDTO.setLastname("Moustakas");
    }

    @AfterEach
    void tearDown() {
        authorUpdateDTO.setId(null);
        authorUpdateDTO.setFirstname(null);
        authorUpdateDTO.setLastname(null);
    }

    // Tests

    @Test
    void testValidValues(){

        // Assert
        assertAll(
                () -> assertNotNull(authorUpdateDTO.getId()),
                () -> assertNotNull(authorUpdateDTO.getFirstname()),
                () -> assertNotNull(authorUpdateDTO.getLastname()),
                () -> assertTrue(validator.validate(authorUpdateDTO).isEmpty()),
                () -> assertEquals(0, validator.validate(authorUpdateDTO).size())
        );
    }

    @Test
    void testBelowMinValues() {
        // Act
        authorUpdateDTO.setFirstname("");
        authorUpdateDTO.setLastname("");

        // Assert
        assertAll(
                () -> assertNotNull(authorUpdateDTO.getFirstname()),
                () -> assertNotNull(authorUpdateDTO.getLastname()),
                () -> assertFalse(validator.validate(authorUpdateDTO).isEmpty()),
                () -> assertEquals(2, validator.validate(authorUpdateDTO).size())
        );

    }

    @Test
    void testAboveMaxValues() {
        // Act
        authorUpdateDTO.setFirstname("%MVTiZz?Z1;;VAM94g7KFH,EKJCFeNhBxP}Q[S9dQ5JR[T");
        authorUpdateDTO.setLastname("%MVTiZz?Z1;;VAM94g7KFH,EKJCFeNhBxP}Q[S9dQ5JR[T");

        // Assert
        assertAll(
                () -> assertNotNull(authorUpdateDTO.getFirstname()),
                () -> assertNotNull(authorUpdateDTO.getLastname()),
                () -> assertFalse(validator.validate(authorUpdateDTO).isEmpty()),
                () -> assertEquals(2, validator.validate(authorUpdateDTO).size())
        );
    }

    @Test
    void testNullValues() {
        // Act
        authorUpdateDTO.setId(null);
        authorUpdateDTO.setFirstname(null);
        authorUpdateDTO.setLastname(null);

        // Assert
        assertAll(
                () -> assertNull(authorUpdateDTO.getId()),
                () -> assertNull(authorUpdateDTO.getFirstname()),
                () -> assertNull(authorUpdateDTO.getLastname()),
                () -> assertFalse(validator.validate(authorUpdateDTO).isEmpty()),
                () -> assertEquals(3, validator.validate(authorUpdateDTO).size())
        );

    }

}