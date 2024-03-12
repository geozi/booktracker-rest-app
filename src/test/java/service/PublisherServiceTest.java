package service;

import dao.util.DatabaseHelper;
import dao.util.JPAHelper;
import model.Publisher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.dto.delete.PublisherDeleteDTO;
import service.dto.insert.PublisherInsertDTO;
import service.dto.update.PublisherUpdateDTO;

import static org.junit.jupiter.api.Assertions.*;

class PublisherServiceTest {
    private static IPublisherService publisherService;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        publisherService = new PublisherServiceImpl();
    }

    @BeforeEach
    void setUp() throws Exception {
        createDummyData();
    }

    @AfterEach
    void tearDown() {
        JPAHelper.getEntityManager().clear();
        DatabaseHelper.eraseData();
    }

    @Test
    void insertPublisher() throws Exception{
        // Arrange
        PublisherInsertDTO pub3 = new PublisherInsertDTO();
        pub3.setName("Another Publisher");
        pub3.setPhoneNumber("6900000003");
        pub3.setEmail("another@publisher.com");
        pub3.setStreetAddress("Akropolis 3");
        pub3.setCity("Athens");
        pub3.setUrl("https://anotherpublisher.com");

        // Act
       Publisher insertedPublisher = publisherService.insertPublisher(pub3);

        // Assert
        assertAll(
                () -> assertNotNull(insertedPublisher),
                () -> assertEquals(3L, insertedPublisher.getId()),
                () -> assertEquals("Another Publisher", insertedPublisher.getName()),
                () -> assertEquals(3, publisherService.getAllPublishers().size())
        );
    }

    @Test
    void updatePublisher() throws Exception {
        // Arrange
        PublisherUpdateDTO publisherToUpdate = new PublisherUpdateDTO();
        publisherToUpdate.setId(2L);
        publisherToUpdate.setName("Updated Publisher");

        // Act
        Publisher updatedPublisher = publisherService.updatePublisher(publisherToUpdate);

        // Assert
        assertAll(
                () -> assertNotNull(updatedPublisher),
                () -> assertEquals(2L, updatedPublisher.getId()),
                () -> assertEquals("Updated Publisher", updatedPublisher.getName())
        );
    }

    @Test
    void deletePublisher() throws Exception {
        // Arrange
        PublisherDeleteDTO publisherToDelete = new PublisherDeleteDTO();
        publisherToDelete.setId(2L);

        // Act
        publisherService.deletePublisher(publisherToDelete);

        // Assert
        assertEquals(1, publisherService.getAllPublishers().size());
    }

    @Test
    void getPublisherById() throws Exception {
        // Act
        Publisher publisher = publisherService.getPublisherById(1L);

        // Assert
        assertAll(
                () -> assertNotNull(publisher),
                () -> assertEquals("Super Publisher", publisher.getName())
        );
    }

    // Tests

    public static void createDummyData() throws Exception {
        PublisherInsertDTO pub1 = new PublisherInsertDTO();
        pub1.setName("Super Publisher");
        pub1.setPhoneNumber("6900000001");
        pub1.setEmail("super@publisher.com");
        pub1.setStreetAddress("Akropolis 1");
        pub1.setCity("Athens");
        pub1.setUrl("https://superpublisher.com");

        publisherService.insertPublisher(pub1);

        PublisherInsertDTO pub2 = new PublisherInsertDTO();
        pub2.setName("Top Publisher");
        pub2.setPhoneNumber("6900000002");
        pub2.setEmail("top@publisher.com");
        pub2.setStreetAddress("Akropolis 2");
        pub2.setCity("Athens");
        pub2.setUrl("https://toppublisher.com");

        publisherService.insertPublisher(pub2);
    }

}