package service.dto.readonly;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublisherReadOnlyDTOTest {
    private static PublisherReadOnlyDTO publisherReadOnlyDTO;
    private static Validator validator;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        publisherReadOnlyDTO = new PublisherReadOnlyDTO();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @BeforeEach
    void setUp() {
        publisherReadOnlyDTO.setId(1L);
        publisherReadOnlyDTO.setName("Small Publishing House");
        publisherReadOnlyDTO.setPhoneNumber("6900000001");
        publisherReadOnlyDTO.setStreetAddress("Imaginary Address 1");
        publisherReadOnlyDTO.setEmail("imaginary@publishing.com");
        publisherReadOnlyDTO.setCity("Capital City");
        publisherReadOnlyDTO.setUrl("https://smallpublishinghouse.com");
    }

    @AfterEach
    void tearDown() {
        publisherReadOnlyDTO.setId(null);
        publisherReadOnlyDTO.setName(null);
        publisherReadOnlyDTO.setPhoneNumber(null);
        publisherReadOnlyDTO.setStreetAddress(null);
        publisherReadOnlyDTO.setEmail(null);
        publisherReadOnlyDTO.setCity(null);
        publisherReadOnlyDTO.setUrl(null);
    }

    // Tests

    @Test
    void testValidValues(){
        //Assert
        assertAll(
                () -> assertNotNull(publisherReadOnlyDTO.getId()),
                () -> assertNotNull(publisherReadOnlyDTO.getName()),
                () -> assertNotNull(publisherReadOnlyDTO.getPhoneNumber()),
                () -> assertNotNull(publisherReadOnlyDTO.getEmail()),
                () -> assertNotNull(publisherReadOnlyDTO.getStreetAddress()),
                () -> assertNotNull(publisherReadOnlyDTO.getCity()),
                () -> assertNotNull(publisherReadOnlyDTO.getUrl()),
                () -> assertTrue(validator.validate(publisherReadOnlyDTO).isEmpty()),
                () -> assertEquals(0, validator.validate(publisherReadOnlyDTO).size())
        );
    }

    @Test
    void testBelowMinValues() {
        // Act
        publisherReadOnlyDTO.setName("");
        publisherReadOnlyDTO.setPhoneNumber("");
        publisherReadOnlyDTO.setStreetAddress("");
        publisherReadOnlyDTO.setEmail("");
        publisherReadOnlyDTO.setCity("");
        publisherReadOnlyDTO.setUrl("");

        //Assert
        assertAll(
                () -> assertNotNull(publisherReadOnlyDTO.getName()),
                () -> assertNotNull(publisherReadOnlyDTO.getPhoneNumber()),
                () -> assertNotNull(publisherReadOnlyDTO.getStreetAddress()),
                () -> assertNotNull(publisherReadOnlyDTO.getEmail()),
                () -> assertNotNull(publisherReadOnlyDTO.getCity()),
                () -> assertNotNull(publisherReadOnlyDTO.getUrl()),
                () -> assertFalse(validator.validate(publisherReadOnlyDTO).isEmpty()),
                () -> assertEquals(6, validator.validate(publisherReadOnlyDTO).size())
        );

    }

    @Test
    void testAboveMaxValues() {
        // Act
        publisherReadOnlyDTO.setName("%MVTiZz?Z1;;VAM94g7KFH,EKJCFeNhBxP}Q[S9dQ5JR[T");
        publisherReadOnlyDTO.setPhoneNumber("619311105395818846601");
        publisherReadOnlyDTO.setEmail("%MVTiZz?Z1;;VAM94g7KFH,EKJCFeNhBxP}Q[S9dQ5JR[T");
        publisherReadOnlyDTO.setCity("%MVTiZz?Z1;;VAM94g7KFH,EKJCFeNhBxP}Q[S9dQ5JR[T");

        //Assert
        assertAll(
                () -> assertNotNull(publisherReadOnlyDTO.getName()),
                () -> assertNotNull(publisherReadOnlyDTO.getPhoneNumber()),
                () -> assertNotNull(publisherReadOnlyDTO.getEmail()),
                () -> assertNotNull(publisherReadOnlyDTO.getCity()),
                () -> assertFalse(validator.validate(publisherReadOnlyDTO).isEmpty()),
                () -> assertEquals(4, validator.validate(publisherReadOnlyDTO).size())
        );

    }

    @Test
    void testNullValues() {
        // Act
        publisherReadOnlyDTO.setId(null);
        publisherReadOnlyDTO.setName(null);
        publisherReadOnlyDTO.setPhoneNumber(null);
        publisherReadOnlyDTO.setStreetAddress(null);
        publisherReadOnlyDTO.setEmail(null);
        publisherReadOnlyDTO.setCity(null);
        publisherReadOnlyDTO.setUrl(null);

        //Assert
        assertAll(
                () -> assertNull(publisherReadOnlyDTO.getId()),
                () -> assertNull(publisherReadOnlyDTO.getName()),
                () -> assertNull(publisherReadOnlyDTO.getPhoneNumber()),
                () -> assertNull(publisherReadOnlyDTO.getEmail()),
                () -> assertNull(publisherReadOnlyDTO.getStreetAddress()),
                () -> assertNull(publisherReadOnlyDTO.getCity()),
                () -> assertNull(publisherReadOnlyDTO.getUrl()),
                () -> assertFalse(validator.validate(publisherReadOnlyDTO).isEmpty()),
                () -> assertEquals(7, validator.validate(publisherReadOnlyDTO).size())
        );
    }

}