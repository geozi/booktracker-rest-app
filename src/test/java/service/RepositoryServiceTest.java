package service;

import dao.util.DatabaseHelper;
import dao.util.JPAHelper;
import model.Repository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.dto.delete.RepositoryDeleteDTO;
import service.dto.insert.RepositoryInsertDTO;
import service.dto.update.RepositoryUpdateDTO;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryServiceTest {
    private static IRepositoryService repoService;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        repoService = new RepositoryServiceImpl();
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

    // Tests

    @Test
    void insertRepo() throws Exception{
        // Arrange
        RepositoryInsertDTO repo3 = new RepositoryInsertDTO();
        repo3.setName("DiVA");
        repo3.setUrl("https://www.diva-portal.org/smash/search.jsf?dswid=-2527");

        // Act
        Repository insertedRepo = repoService.insertRepo(repo3);

        // Assert
        assertAll(
                () -> assertNotNull(insertedRepo),
                () -> assertEquals("DiVA", insertedRepo.getName()),
                () -> assertEquals(3, repoService.getAllRepos().size())
        );

    }

    @Test
    void updateRepo() throws Exception {
        // Arrange
        RepositoryUpdateDTO repoToUpdate = new RepositoryUpdateDTO();
        repoToUpdate.setId(1L);
        repoToUpdate.setName("DiVA");
        repoToUpdate.setUrl("https://www.diva-portal.org/smash/search.jsf?dswid=-2527");

        // Act
        Repository updatedRepo = repoService.updateRepo(repoToUpdate);

        // Assert
        assertAll(
                () -> assertNotNull(updatedRepo),
                () -> assertEquals(1L, updatedRepo.getId()),
                () -> assertEquals("DiVA", updatedRepo.getName())
        );
    }

    @Test
    void deleteRepo() throws Exception {
        // Arrange
        RepositoryDeleteDTO repoToDelete = new RepositoryDeleteDTO();
        repoToDelete.setId(2L);

        // Act
        repoService.deleteRepo(repoToDelete);

        // Assert
        assertAll(
                () -> assertNotNull(repoService.getRepoById(1L)),
                () -> assertEquals(1, repoService.getAllRepos().size())
        );

    }

    @Test
    void getRepoById() throws Exception{
        // Act
        Repository repo = repoService.getRepoById(1L);

        // Assert
        assertAll(
                () -> assertNotNull(repo),
                () -> assertEquals(1L, repo.getId()),
                () -> assertEquals("Kallipos", repo.getName())
        );
    }


    public static void createDummyData() throws Exception {

        RepositoryInsertDTO repo1 = new RepositoryInsertDTO();
        repo1.setName("Kallipos");
        repo1.setUrl("https://repository.kallipos.gr/");
        repoService.insertRepo(repo1);

        RepositoryInsertDTO repo2 = new RepositoryInsertDTO();
        repo2.setName("OAPEN");
        repo2.setUrl("https://www.oapen.org/");
        repoService.insertRepo(repo2);

    }
}