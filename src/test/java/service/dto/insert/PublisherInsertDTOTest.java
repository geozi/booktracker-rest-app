package service.dto.insert;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublisherInsertDTOTest {
    private static PublisherInsertDTO publisherInsertDTO;
    private static Validator validator;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        publisherInsertDTO = new PublisherInsertDTO();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @BeforeEach
    void setUp() {
        publisherInsertDTO.setName("Penguin Books");
        publisherInsertDTO.setPhoneNumber("+442070628940");
        publisherInsertDTO.setEmail("consumerservices@penguinrandomhouse.com");
        publisherInsertDTO.setStreetAddress("Embassy Gardens");
        publisherInsertDTO.setCity("London");
        publisherInsertDTO.setUrl("https://www.penguin.co.uk/");
    }

    @AfterEach
    void tearDown() {
        publisherInsertDTO.setName(null);
        publisherInsertDTO.setPhoneNumber(null);
        publisherInsertDTO.setEmail(null);
        publisherInsertDTO.setStreetAddress(null);
        publisherInsertDTO.setCity(null);
        publisherInsertDTO.setUrl(null);
    }

    // Tests

    @Test
    void testValidValues(){
        // Assert
        assertAll(
                () -> assertNotNull(publisherInsertDTO.getName()),
                () -> assertNotNull(publisherInsertDTO.getPhoneNumber()),
                () -> assertNotNull(publisherInsertDTO.getEmail()),
                () -> assertNotNull(publisherInsertDTO.getStreetAddress()),
                () -> assertNotNull(publisherInsertDTO.getCity()),
                () -> assertNotNull(publisherInsertDTO.getUrl()),
                () -> assertTrue(validator.validate(publisherInsertDTO).isEmpty()),
                () -> assertEquals(0,validator.validate(publisherInsertDTO).size())
        );
    }

    @Test
    void testBelowMinValues() {
        // Act
        publisherInsertDTO.setName("");
        publisherInsertDTO.setPhoneNumber("");
        publisherInsertDTO.setEmail("");
        publisherInsertDTO.setStreetAddress("");
        publisherInsertDTO.setCity("");
        publisherInsertDTO.setUrl("");

        // Assert
        assertAll(
                () -> assertNotNull(publisherInsertDTO.getName()),
                () -> assertNotNull(publisherInsertDTO.getPhoneNumber()),
                () -> assertNotNull(publisherInsertDTO.getEmail()),
                () -> assertNotNull(publisherInsertDTO.getStreetAddress()),
                () -> assertNotNull(publisherInsertDTO.getCity()),
                () -> assertNotNull(publisherInsertDTO.getUrl()),
                () -> assertFalse(validator.validate(publisherInsertDTO).isEmpty()),
                () -> assertEquals(6, validator.validate(publisherInsertDTO).size())
        );
    }

    @Test
    void testAboveMaxValues() {
        // Act
        publisherInsertDTO.setName("zwcwnqnegtyyxkozuiiltdeeduhwizskvxorafdkml1111");
        publisherInsertDTO.setPhoneNumber("69000000001690000000011");
        publisherInsertDTO.setEmail("zwcwnqnegtyyxkozuiiltdeeduhwizskvxorafdkml1111");
        publisherInsertDTO.setCity("zwcwnqnegtyyxkozuiiltdeeduhwizskvxorafdkml1111");

        // Assert
        assertAll(
                () -> assertNotNull(publisherInsertDTO.getName()),
                () -> assertNotNull(publisherInsertDTO.getPhoneNumber()),
                () -> assertNotNull(publisherInsertDTO.getEmail()),
                () -> assertNotNull(publisherInsertDTO.getCity()),
                () -> assertFalse(validator.validate(publisherInsertDTO).isEmpty()),
                () -> assertEquals(4, validator.validate(publisherInsertDTO).size())
        );
    }

    @Test
    void testNullValues() {
        // Act
        publisherInsertDTO.setName(null);
        publisherInsertDTO.setPhoneNumber(null);
        publisherInsertDTO.setEmail(null);
        publisherInsertDTO.setStreetAddress(null);
        publisherInsertDTO.setCity(null);
        publisherInsertDTO.setUrl(null);

        // Assert
        assertAll(
                () -> assertNull(publisherInsertDTO.getName()),
                () -> assertNull(publisherInsertDTO.getPhoneNumber()),
                () -> assertNull(publisherInsertDTO.getEmail()),
                () -> assertNull(publisherInsertDTO.getStreetAddress()),
                () -> assertNull(publisherInsertDTO.getCity()),
                () -> assertNull(publisherInsertDTO.getUrl()),
                () -> assertFalse(validator.validate(publisherInsertDTO).isEmpty()),
                () -> assertEquals(6, validator.validate(publisherInsertDTO).size())
        );
    }

}