package service.dto.insert;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryInsertDTOTest {
    private static RepositoryInsertDTO repositoryInsertDTO;
    private static Validator validator;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        repositoryInsertDTO = new RepositoryInsertDTO();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @BeforeEach
    void setUp() {
        repositoryInsertDTO.setName("Kallipos");
        repositoryInsertDTO.setUrl("https://repository.kallipos.gr/");
    }

    @AfterEach
    void tearDown() {
        repositoryInsertDTO.setName(null);
        repositoryInsertDTO.setUrl(null);
    }

    // Tests

    @Test
    void testValidValues(){

        // Assert
        assertAll(
                () -> assertNotNull(repositoryInsertDTO.getName()),
                () -> assertNotNull(repositoryInsertDTO.getUrl()),
                () -> assertTrue(validator.validate(repositoryInsertDTO).isEmpty()),
                () -> assertEquals(0, validator.validate(repositoryInsertDTO).size())
        );
    }

    @Test
    void testBelowMinValues() {
        // Act
        repositoryInsertDTO.setName("");
        repositoryInsertDTO.setUrl("");

        // Assert
        assertAll(
                () -> assertNotNull(repositoryInsertDTO.getName()),
                () -> assertNotNull(repositoryInsertDTO.getUrl()),
                () -> assertFalse(validator.validate(repositoryInsertDTO).isEmpty()),
                () -> assertEquals(2, validator.validate(repositoryInsertDTO).size())
        );

    }

    @Test
    void testNullValues() {
        // Act
        repositoryInsertDTO.setName(null);
        repositoryInsertDTO.setUrl(null);

        // Assert
        assertAll(
                () -> assertNull(repositoryInsertDTO.getName()),
                () -> assertNull(repositoryInsertDTO.getUrl()),
                () -> assertFalse(validator.validate(repositoryInsertDTO).isEmpty()),
                () -> assertEquals(2, validator.validate(repositoryInsertDTO).size())
        );
    }

}