package dao;

import dao.util.DatabaseHelper;
import dao.util.JPAHelper;
import model.Publisher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublisherDAOTest {
    private static IGenericDAO<Publisher> publisherDAO;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        publisherDAO = new PublisherDAOImpl();
    }

    @BeforeEach
    void setUp() {
        createDummyData();
    }

    @AfterEach
    void tearDown() {
        JPAHelper.getEntityManager().clear();
        DatabaseHelper.eraseData();
    }

    // Tests

    @Test
    void insertPublisher() {
        JPAHelper.beginTransaction();

        // Arrange
        Publisher pub3 = new Publisher();
        pub3.setName("Best Publisher");
        pub3.setPhoneNumber("6900000003");
        pub3.setEmail("best@publisher.com");
        pub3.setStreetAddress("Akriani 2");
        pub3.setCity("Athens");
        pub3.setUrl("https://bestpublisher.com");

        // Act
        publisherDAO.insert(pub3);

        // Assert
        assertEquals(3, publisherDAO.getAll().size());

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();
    }

    @Test
    void getAndUpdatePublisher() {
        JPAHelper.beginTransaction();

        // Arrange
        Publisher publisherToUpdate = publisherDAO.getById(1L);
        publisherToUpdate.setName("Updated Publisher");

        // Act
        publisherDAO.update(publisherToUpdate);

        // Assert
        assertAll(
                () -> assertNotNull(publisherDAO.getById(1L)),
                () -> assertEquals("Updated Publisher", publisherDAO.getById(1L).getName())
        );

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();
    }

    @Test
    void deletePublisher() {
        JPAHelper.beginTransaction();

        // Act
        publisherDAO.delete(2L);

        // Assert
        assertNull(publisherDAO.getById(2L));

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();

    }

    @Test
    void getPublisherByName() {
        JPAHelper.beginTransaction();

        // Assert
        assertEquals(1, publisherDAO.getByKeyword("New Publisher").size());

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();
    }

    public static void createDummyData() {
        JPAHelper.beginTransaction();

        Publisher pub1 = new Publisher();
        pub1.setName("New Publisher");
        pub1.setPhoneNumber("6900000001");
        pub1.setEmail("new@publisher.com");
        pub1.setStreetAddress("Akropolis 1");
        pub1.setCity("Athens");
        pub1.setUrl("https://newpublisher.com");
        publisherDAO.insert(pub1);

        Publisher pub2 = new Publisher();
        pub2.setName("Your Publisher");
        pub2.setPhoneNumber("6900000002");
        pub2.setEmail("your@random.com");
        pub2.setStreetAddress("New Orleani 12");
        pub2.setCity("Athens");
        pub2.setUrl("https://yourpublisher.com");
        publisherDAO.insert(pub2);

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();
    }
}