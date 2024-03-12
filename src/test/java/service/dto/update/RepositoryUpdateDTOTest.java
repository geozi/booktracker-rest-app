package service.dto.update;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryUpdateDTOTest {
    private static RepositoryUpdateDTO repositoryUpdateDTO;
    private static Validator validator;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        repositoryUpdateDTO = new RepositoryUpdateDTO();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @BeforeEach
    void setUp() {
        repositoryUpdateDTO.setId(1L);
        repositoryUpdateDTO.setName("Kallipos");
        repositoryUpdateDTO.setUrl("https://repository.kallipos.gr/");
    }

    @AfterEach
    void tearDown () {
        repositoryUpdateDTO.setId(null);
        repositoryUpdateDTO.setName(null);
        repositoryUpdateDTO.setUrl(null);
    }

    // Tests

    @Test
    void testValidValues(){
        // Assert
        assertAll(
                () -> assertNotNull(repositoryUpdateDTO.getId()),
                () -> assertNotNull(repositoryUpdateDTO.getName()),
                () -> assertNotNull(repositoryUpdateDTO.getUrl()),
                () -> assertTrue(validator.validate(repositoryUpdateDTO).isEmpty()),
                () -> assertEquals(0,validator.validate(repositoryUpdateDTO).size() )
        );
    }

    @Test
    void testBelowMinValues() {
        // Act
        repositoryUpdateDTO.setName("");
        repositoryUpdateDTO.setUrl("");

        // Assert
        assertAll(
                () -> assertNotNull(repositoryUpdateDTO.getName()),
                () -> assertNotNull(repositoryUpdateDTO.getUrl()),
                () -> assertFalse(validator.validate(repositoryUpdateDTO).isEmpty()),
                () -> assertEquals(2,validator.validate(repositoryUpdateDTO).size() )
        );
    }


    @Test
    void testNullValues() {
        // Act
        repositoryUpdateDTO.setId(null);
        repositoryUpdateDTO.setName(null);
        repositoryUpdateDTO.setUrl(null);

        // Assert
        assertAll(
                () -> assertNull(repositoryUpdateDTO.getId()),
                () -> assertNull(repositoryUpdateDTO.getName()),
                () -> assertNull(repositoryUpdateDTO.getUrl()),
                () -> assertFalse(validator.validate(repositoryUpdateDTO).isEmpty()),
                () -> assertEquals(3,validator.validate(repositoryUpdateDTO).size() )
        );
    }

}