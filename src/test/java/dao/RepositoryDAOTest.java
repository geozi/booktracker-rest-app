package dao;

import dao.util.DatabaseHelper;
import dao.util.JPAHelper;
import model.Repository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryDAOTest {
    private static IGenericDAO<Repository> repoDAO;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        repoDAO = new RepositoryDAOImpl();
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
    void insertRepo() {
        JPAHelper.beginTransaction();

        // Arrange
        Repository repo3 = new Repository();
        repo3.setName("OAPEN");
        repo3.setUrl("https://www.oapen.org/");

        // Act
        repoDAO.insert(repo3);

        // Assert
        assertEquals(3, repoDAO.getAll().size());

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();
    }

    @Test
    void getAndUpdateRepo() {
        JPAHelper.beginTransaction();

        // Arrange
        Repository repoToUpdate = repoDAO.getById(2L);
        repoToUpdate.setName("eSholarship");
        repoToUpdate.setUrl("https://escholarship.org/");

        // Act
        repoDAO.update(repoToUpdate);

        // Assert
        assertAll(
                () -> assertNotNull(repoDAO.getById(2L)),
                () -> assertEquals("eSholarship", repoDAO.getById(2L).getName())
        );

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();
    }

    @Test
    void deleteRepo() {
        JPAHelper.beginTransaction();

        // Act
        repoDAO.delete(2L);

        // Assert
        assertNull(repoDAO.getById(2L));

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();
    }

    @Test
    void getRepoByName() {
        JPAHelper.beginTransaction();

        // Assert
        assertEquals(1, repoDAO.getByKeyword("Kallipos").size());

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();
    }

    public static void createDummyData() {
        JPAHelper.beginTransaction();

        Repository repo1 = new Repository();
        repo1.setName("Open Textbook Library");
        repo1.setUrl("https://open.umn.edu/opentextbooks");
        repoDAO.insert(repo1);

        Repository repo2 = new Repository();
        repo2.setName("Kallipos");
        repo2.setUrl("https://repository.kallipos.gr/");
        repoDAO.insert(repo2);

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();

    }
}