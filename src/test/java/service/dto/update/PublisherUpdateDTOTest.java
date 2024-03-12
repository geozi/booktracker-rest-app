package service.dto.update;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class PublisherUpdateDTOTest {
    private static PublisherUpdateDTO publisherUpdateDTO;
    private static Validator validator;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        publisherUpdateDTO = new PublisherUpdateDTO();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @BeforeEach
    void setUp() {
        publisherUpdateDTO.setId(1L);
        publisherUpdateDTO.setName("Small Publishing House");
        publisherUpdateDTO.setPhoneNumber("6900000001");
        publisherUpdateDTO.setStreetAddress("Imaginary Address 1");
        publisherUpdateDTO.setEmail("imaginary@publishing.com");
        publisherUpdateDTO.setCity("Capital City");
        publisherUpdateDTO.setUrl("https://smallpublishinghouse.com");
    }

    @AfterEach
    void tearDown() {
        publisherUpdateDTO.setId(null);
        publisherUpdateDTO.setName(null);
        publisherUpdateDTO.setPhoneNumber(null);
        publisherUpdateDTO.setStreetAddress(null);
        publisherUpdateDTO.setEmail(null);
        publisherUpdateDTO.setCity(null);
        publisherUpdateDTO.setUrl(null);
    }

    // Tests

    @Test
    void testValidValues(){
        //Assert
        assertAll(
                () -> assertNotNull(publisherUpdateDTO.getId()),
                () -> assertNotNull(publisherUpdateDTO.getName()),
                () -> assertNotNull(publisherUpdateDTO.getPhoneNumber()),
                () -> assertNotNull(publisherUpdateDTO.getEmail()),
                () -> assertNotNull(publisherUpdateDTO.getStreetAddress()),
                () -> assertNotNull(publisherUpdateDTO.getCity()),
                () -> assertNotNull(publisherUpdateDTO.getUrl()),
                () -> assertTrue(validator.validate(publisherUpdateDTO).isEmpty()),
                () -> assertEquals(0, validator.validate(publisherUpdateDTO).size())
        );
    }

    @Test
    void testBelowMinValues() {
        // Act
        publisherUpdateDTO.setName("");
        publisherUpdateDTO.setPhoneNumber("");
        publisherUpdateDTO.setStreetAddress("");
        publisherUpdateDTO.setEmail("");
        publisherUpdateDTO.setCity("");
        publisherUpdateDTO.setUrl("");

        //Assert
        assertAll(
                () -> assertNotNull(publisherUpdateDTO.getName()),
                () -> assertNotNull(publisherUpdateDTO.getPhoneNumber()),
                () -> assertNotNull(publisherUpdateDTO.getStreetAddress()),
                () -> assertNotNull(publisherUpdateDTO.getEmail()),
                () -> assertNotNull(publisherUpdateDTO.getCity()),
                () -> assertNotNull(publisherUpdateDTO.getUrl()),
                () -> assertFalse(validator.validate(publisherUpdateDTO).isEmpty()),
                () -> assertEquals(6, validator.validate(publisherUpdateDTO).size())
        );

    }

    @Test
    void testAboveMaxValues() {
        // Act
        publisherUpdateDTO.setName("%MVTiZz?Z1;;VAM94g7KFH,EKJCFeNhBxP}Q[S9dQ5JR[T");
        publisherUpdateDTO.setPhoneNumber("619311105395818846601");
        publisherUpdateDTO.setEmail("%MVTiZz?Z1;;VAM94g7KFH,EKJCFeNhBxP}Q[S9dQ5JR[T");
        publisherUpdateDTO.setCity("%MVTiZz?Z1;;VAM94g7KFH,EKJCFeNhBxP}Q[S9dQ5JR[T");

        //Assert
        assertAll(
                () -> assertNotNull(publisherUpdateDTO.getName()),
                () -> assertNotNull(publisherUpdateDTO.getPhoneNumber()),
                () -> assertNotNull(publisherUpdateDTO.getEmail()),
                () -> assertNotNull(publisherUpdateDTO.getCity()),
                () -> assertFalse(validator.validate(publisherUpdateDTO).isEmpty()),
                () -> assertEquals(4, validator.validate(publisherUpdateDTO).size())
        );

    }

    @Test
    void testNullValues() {
        // Act
        publisherUpdateDTO.setId(null);
        publisherUpdateDTO.setName(null);
        publisherUpdateDTO.setPhoneNumber(null);
        publisherUpdateDTO.setStreetAddress(null);
        publisherUpdateDTO.setEmail(null);
        publisherUpdateDTO.setCity(null);
        publisherUpdateDTO.setUrl(null);

        //Assert
        assertAll(
                () -> assertNull(publisherUpdateDTO.getId()),
                () -> assertNull(publisherUpdateDTO.getName()),
                () -> assertNull(publisherUpdateDTO.getPhoneNumber()),
                () -> assertNull(publisherUpdateDTO.getEmail()),
                () -> assertNull(publisherUpdateDTO.getStreetAddress()),
                () -> assertNull(publisherUpdateDTO.getCity()),
                () -> assertNull(publisherUpdateDTO.getUrl()),
                () -> assertFalse(validator.validate(publisherUpdateDTO).isEmpty()),
                () -> assertEquals(7, validator.validate(publisherUpdateDTO).size())
        );
    }

}