package model;

import model.enumfields.FormatType;
import model.enumfields.LanguageType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class PublisherModelTest {
    private static Publisher publisher;

    // Setting test prep conditions

    @BeforeAll
    static void setUpTesting() {
        publisher = new Publisher();
    }

    @BeforeEach
    void setUp() {
        publisher.setId(1L);
        publisher.setName("Random Publishing House");
        publisher.setPhoneNumber("6900000001");
        publisher.setEmail("random@publishing.org");
        publisher.setStreetAddress("Akropolis 1");
        publisher.setCity("Athens");
        publisher.setUrl("https://randompublishinghouse.org");
    }

    @AfterEach
    void tearDown() {
        publisher.setId(0L);
        publisher.setName(null);
        publisher.setPhoneNumber(null);
        publisher.setEmail(null);
        publisher.setStreetAddress(null);
        publisher.setCity(null);
        publisher.setUrl(null);
        publisher.getEditions().clear();
    }

    // Testing getters

    @Test
    void getId() {
        assertEquals(1, publisher.getId());
    }

    @Test
    void getName() {
        assertNotNull(publisher.getName());
    }

    @Test
    void getPhoneNumber() {
        assertNotNull(publisher.getPhoneNumber());
    }

    @Test
    void getEmail() {
        assertNotNull(publisher.getEmail());
    }

    @Test
    void getStreetAddress() {
        assertNotNull(publisher.getStreetAddress());
    }

    @Test
    void getCity() {
        assertNotNull(publisher.getCity());
    }

    @Test
    void getUrl() {
        assertNotNull(publisher.getUrl());
    }

    @Test
    void getEditions() {
        assertEquals(0, publisher.getEditions().size());
    }

    // Testing setters

    @Test
    void modifyName() {
        publisher.setName("New Publisher");
        assertAll(
                () -> assertNotNull(publisher.getName()),
                () -> assertEquals("New Publisher", publisher.getName())
        );
    }

    @Test
    void modifyPhoneNumber() {
        publisher.setPhoneNumber("6901000000");
        assertAll(
                () -> assertNotNull(publisher.getPhoneNumber()),
                () -> assertEquals(10, publisher.getPhoneNumber().length()),
                () -> assertEquals("6901000000", publisher.getPhoneNumber())
        );
    }

    @Test
    void modifyEmail() {
        publisher.setEmail("new@email.org");
        assertAll(
                () -> assertNotNull(publisher.getEmail()),
                () -> assertEquals("new@email.org", publisher.getEmail())
        );
    }

    @Test
    void modifyStreetAddress() {
        publisher.setStreetAddress("Nea Orleani 23");
        assertAll(
                () -> assertNotNull(publisher.getStreetAddress()),
                () -> assertEquals("Nea Orleani 23", publisher.getStreetAddress())
        );
    }

    @Test
    void modifyCity() {
        publisher.setCity("Thessaloniki");
        assertAll(
                () -> assertNotNull(publisher.getCity()),
                () -> assertEquals("Thessaloniki", publisher.getCity())
        );
    }

    @Test
    void modifyUrl() {
        publisher.setUrl("https://randomurl.com");
        assertAll(
                () -> assertNotNull(publisher.getUrl()),
                () -> assertEquals("https://randomurl.com", publisher.getUrl())
        );
    }

    // Testing convenient methods

    @Test
    void addEdition() {
        LocalDate date = LocalDate.parse("10-Feb-2024", DateTimeFormatter.ofPattern("d-MMM-yyyy"));
        Edition edition = new Edition();
        edition.setFormat(FormatType.EBOOK);
        edition.setPublicationDate(date);
        edition.setPageCount(300);
        edition.setLanguage(LanguageType.ENGLISH);
        publisher.addEdition(edition);
        assertEquals(1, publisher.getAllEditions().size());
    }

}