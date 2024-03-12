package dao;

import dao.util.DatabaseHelper;
import dao.util.JPAHelper;
import model.Author;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class AuthorDAOTest {
    private static IGenericDAO<Author> authorDAO;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        authorDAO = new AuthorDAOImpl();
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
    void insertAuthor() {
        JPAHelper.beginTransaction();

        // Arrange
        Author ar4 = new Author();
        ar4.setFirstname("Marianna");
        ar4.setLastname("Thompson");

        // Act
        authorDAO.insert(ar4);

        // Assert
        assertEquals(4, authorDAO.getAll().size());

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();
    }

    @Test
    void getAndUpdateAuthor() {
        JPAHelper.beginTransaction();

        // Arrange
        Author authorToUpdate = authorDAO.getById(3L);
        authorToUpdate.setFirstname("Nina");
        authorToUpdate.setLastname("Ramos");

        // Act
        authorDAO.update(authorToUpdate);

        // Assert
        assertAll(
                () -> assertNotNull(authorDAO.getById(3L)),
                () -> assertEquals("Ramos", authorDAO.getById(3L).getLastname())
        );

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();
    }

    @Test
    void deleteAuthor() {
        JPAHelper.beginTransaction();

        // Act
        authorDAO.delete(2L);

        // Assert
        assertNull(authorDAO.getById(2L));

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();
    }

    @Test
    void getAuthorByLastname() {
        JPAHelper.beginTransaction();

        // Assert
        assertEquals(1, authorDAO.getByKeyword("Lyn").size());

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();
    }


    public static void createDummyData() {

        JPAHelper.beginTransaction();

        Author ar1 = new Author();
        ar1.setFirstname("Maryam");
        ar1.setLastname("Lynn");

        authorDAO.insert(ar1);

        Author ar2 = new Author();
        ar2.setFirstname("Blake");
        ar2.setLastname("Floyd");

        authorDAO.insert(ar2);

        Author ar3 = new Author();
        ar3.setFirstname("Lauren");
        ar3.setLastname("Guerrero");

        authorDAO.insert(ar3);

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();

    }

}