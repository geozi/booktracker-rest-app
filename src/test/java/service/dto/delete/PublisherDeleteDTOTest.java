package service.dto.delete;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublisherDeleteDTOTest {
    private static PublisherDeleteDTO publisherDeleteDTO;
    private static Validator validator;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        publisherDeleteDTO = new PublisherDeleteDTO();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @BeforeEach
    void setUp() {
        publisherDeleteDTO.setId(1L);
        publisherDeleteDTO.setName("Small Publishing House");
        publisherDeleteDTO.setPhoneNumber("6900000001");
        publisherDeleteDTO.setStreetAddress("Imaginary Address 1");
        publisherDeleteDTO.setEmail("imaginary@publishing.com");
        publisherDeleteDTO.setCity("Capital City");
        publisherDeleteDTO.setUrl("https://smallpublishinghouse.com");
    }

    @AfterEach
    void tearDown() {
        publisherDeleteDTO.setId(null);
        publisherDeleteDTO.setName(null);
        publisherDeleteDTO.setPhoneNumber(null);
        publisherDeleteDTO.setStreetAddress(null);
        publisherDeleteDTO.setEmail(null);
        publisherDeleteDTO.setCity(null);
        publisherDeleteDTO.setUrl(null);
    }

    // Tests

    @Test
    void testValidValues(){
        //Assert
        assertAll(
                () -> assertNotNull(publisherDeleteDTO.getId()),
                () -> assertNotNull(publisherDeleteDTO.getName()),
                () -> assertNotNull(publisherDeleteDTO.getPhoneNumber()),
                () -> assertNotNull(publisherDeleteDTO.getEmail()),
                () -> assertNotNull(publisherDeleteDTO.getStreetAddress()),
                () -> assertNotNull(publisherDeleteDTO.getCity()),
                () -> assertNotNull(publisherDeleteDTO.getUrl()),
                () -> assertTrue(validator.validate(publisherDeleteDTO).isEmpty()),
                () -> assertEquals(0, validator.validate(publisherDeleteDTO).size())
        );
    }

    @Test
    void testBelowMinValues() {
        // Act
        publisherDeleteDTO.setName("");
        publisherDeleteDTO.setPhoneNumber("");
        publisherDeleteDTO.setStreetAddress("");
        publisherDeleteDTO.setEmail("");
        publisherDeleteDTO.setCity("");
        publisherDeleteDTO.setUrl("");

        //Assert
        assertAll(
                () -> assertNotNull(publisherDeleteDTO.getName()),
                () -> assertNotNull(publisherDeleteDTO.getPhoneNumber()),
                () -> assertNotNull(publisherDeleteDTO.getStreetAddress()),
                () -> assertNotNull(publisherDeleteDTO.getEmail()),
                () -> assertNotNull(publisherDeleteDTO.getCity()),
                () -> assertNotNull(publisherDeleteDTO.getUrl()),
                () -> assertFalse(validator.validate(publisherDeleteDTO).isEmpty()),
                () -> assertEquals(6, validator.validate(publisherDeleteDTO).size())
        );

    }

    @Test
    void testAboveMaxValues() {
        // Act
        publisherDeleteDTO.setName("%MVTiZz?Z1;;VAM94g7KFH,EKJCFeNhBxP}Q[S9dQ5JR[T");
        publisherDeleteDTO.setPhoneNumber("619311105395818846601");
        publisherDeleteDTO.setEmail("%MVTiZz?Z1;;VAM94g7KFH,EKJCFeNhBxP}Q[S9dQ5JR[T");
        publisherDeleteDTO.setCity("%MVTiZz?Z1;;VAM94g7KFH,EKJCFeNhBxP}Q[S9dQ5JR[T");

        //Assert
        assertAll(
                () -> assertNotNull(publisherDeleteDTO.getName()),
                () -> assertNotNull(publisherDeleteDTO.getPhoneNumber()),
                () -> assertNotNull(publisherDeleteDTO.getEmail()),
                () -> assertNotNull(publisherDeleteDTO.getCity()),
                () -> assertFalse(validator.validate(publisherDeleteDTO).isEmpty()),
                () -> assertEquals(4, validator.validate(publisherDeleteDTO).size())
        );

    }

    @Test
    void testNullValues() {
        // Act
        publisherDeleteDTO.setId(null);
        publisherDeleteDTO.setName(null);
        publisherDeleteDTO.setPhoneNumber(null);
        publisherDeleteDTO.setStreetAddress(null);
        publisherDeleteDTO.setEmail(null);
        publisherDeleteDTO.setCity(null);
        publisherDeleteDTO.setUrl(null);

        //Assert
        assertAll(
                () -> assertNull(publisherDeleteDTO.getId()),
                () -> assertNull(publisherDeleteDTO.getName()),
                () -> assertNull(publisherDeleteDTO.getPhoneNumber()),
                () -> assertNull(publisherDeleteDTO.getEmail()),
                () -> assertNull(publisherDeleteDTO.getStreetAddress()),
                () -> assertNull(publisherDeleteDTO.getCity()),
                () -> assertNull(publisherDeleteDTO.getUrl()),
                () -> assertFalse(validator.validate(publisherDeleteDTO).isEmpty()),
                () -> assertEquals(1, validator.validate(publisherDeleteDTO).size())
        );
    }
}