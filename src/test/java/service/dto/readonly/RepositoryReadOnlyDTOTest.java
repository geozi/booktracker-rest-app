package service.dto.readonly;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryReadOnlyDTOTest {
    private static RepositoryReadOnlyDTO repositoryReadOnlyDTO;
    private static Validator validator;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        repositoryReadOnlyDTO = new RepositoryReadOnlyDTO();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @BeforeEach
    void setUp() {
        repositoryReadOnlyDTO.setId(1L);
        repositoryReadOnlyDTO.setName("Kallipos");
        repositoryReadOnlyDTO.setUrl("https://repository.kallipos.gr/");
    }

    @AfterEach
    void tearDown () {
        repositoryReadOnlyDTO.setId(null);
        repositoryReadOnlyDTO.setName(null);
        repositoryReadOnlyDTO.setUrl(null);
    }

    // Tests

    @Test
    void testValidValues(){
        // Assert
        assertAll(
                () -> assertNotNull(repositoryReadOnlyDTO.getId()),
                () -> assertNotNull(repositoryReadOnlyDTO.getName()),
                () -> assertNotNull(repositoryReadOnlyDTO.getUrl()),
                () -> assertTrue(validator.validate(repositoryReadOnlyDTO).isEmpty()),
                () -> assertEquals(0,validator.validate(repositoryReadOnlyDTO).size() )
        );
    }

    @Test
    void testBelowMinValues() {
        // Act
        repositoryReadOnlyDTO.setName("");
        repositoryReadOnlyDTO.setUrl("");

        // Assert
        assertAll(
                () -> assertNotNull(repositoryReadOnlyDTO.getName()),
                () -> assertNotNull(repositoryReadOnlyDTO.getUrl()),
                () -> assertFalse(validator.validate(repositoryReadOnlyDTO).isEmpty()),
                () -> assertEquals(2,validator.validate(repositoryReadOnlyDTO).size() )
        );
    }


    @Test
    void testNullValues() {
        // Act
        repositoryReadOnlyDTO.setId(null);
        repositoryReadOnlyDTO.setName(null);
        repositoryReadOnlyDTO.setUrl(null);

        // Assert
        assertAll(
                () -> assertNull(repositoryReadOnlyDTO.getId()),
                () -> assertNull(repositoryReadOnlyDTO.getName()),
                () -> assertNull(repositoryReadOnlyDTO.getUrl()),
                () -> assertFalse(validator.validate(repositoryReadOnlyDTO).isEmpty()),
                () -> assertEquals(3,validator.validate(repositoryReadOnlyDTO).size() )
        );
    }
}