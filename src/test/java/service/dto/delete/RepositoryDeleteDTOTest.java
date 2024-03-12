package service.dto.delete;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryDeleteDTOTest {
    private static RepositoryDeleteDTO repositoryDeleteDTO;
    private static Validator validator;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        repositoryDeleteDTO = new RepositoryDeleteDTO();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @BeforeEach
    void setUp() {
        repositoryDeleteDTO.setId(1L);
        repositoryDeleteDTO.setName("Kallipos");
        repositoryDeleteDTO.setUrl("https://repository.kallipos.gr/");
    }

    @AfterEach
    void tearDown () {
        repositoryDeleteDTO.setId(null);
        repositoryDeleteDTO.setName(null);
        repositoryDeleteDTO.setUrl(null);
    }

    // Tests

    @Test
    void testValidValues(){
        // Assert
        assertAll(
                () -> assertNotNull(repositoryDeleteDTO.getId()),
                () -> assertNotNull(repositoryDeleteDTO.getName()),
                () -> assertNotNull(repositoryDeleteDTO.getUrl()),
                () -> assertTrue(validator.validate(repositoryDeleteDTO).isEmpty()),
                () -> assertEquals(0,validator.validate(repositoryDeleteDTO).size() )
        );
    }

    @Test
    void testBelowMinValues() {
        // Act
        repositoryDeleteDTO.setName("");
        repositoryDeleteDTO.setUrl("");

        // Assert
        assertAll(
                () -> assertNotNull(repositoryDeleteDTO.getName()),
                () -> assertNotNull(repositoryDeleteDTO.getUrl()),
                () -> assertFalse(validator.validate(repositoryDeleteDTO).isEmpty()),
                () -> assertEquals(2,validator.validate(repositoryDeleteDTO).size() )
        );
    }


    @Test
    void testNullValues() {
        // Act
        repositoryDeleteDTO.setId(null);
        repositoryDeleteDTO.setName(null);
        repositoryDeleteDTO.setUrl(null);

        // Assert
        assertAll(
                () -> assertNull(repositoryDeleteDTO.getId()),
                () -> assertNull(repositoryDeleteDTO.getName()),
                () -> assertNull(repositoryDeleteDTO.getUrl()),
                () -> assertFalse(validator.validate(repositoryDeleteDTO).isEmpty()),
                () -> assertEquals(1,validator.validate(repositoryDeleteDTO).size() )
        );
    }
}