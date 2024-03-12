package service;

import dao.util.DatabaseHelper;
import dao.util.JPAHelper;
import model.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.dto.delete.AuthorDeleteDTO;
import service.dto.insert.AuthorInsertDTO;
import service.dto.update.AuthorUpdateDTO;

import static org.junit.jupiter.api.Assertions.*;

class AuthorServiceTest {
    private static IAuthorService authorService;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        authorService = new AuthorServiceImpl();
    }

    @BeforeEach
    void setUp() throws Exception{
        createDummyData();
    }

    @AfterEach
    void tearDown() {
        JPAHelper.getEntityManager().clear();
        DatabaseHelper.eraseData();
    }

    // Tests

    @Test
    void insertAuthor() throws Exception {
        // Arrange
        AuthorInsertDTO a4 = new AuthorInsertDTO();
        a4.setFirstname("Alia");
        a4.setLastname("Park");

        // Act
        Author insertedAuthor = authorService.insertAuthor(a4);

        // Assert
        assertAll(
                () -> assertNotNull(insertedAuthor),
                () -> assertEquals("Park", insertedAuthor.getLastname())
        );
    }

    @Test
    void updateAuthor() throws Exception {
        // Arrange
        AuthorUpdateDTO authorToUpdate = new AuthorUpdateDTO();
        authorToUpdate.setId(3L);
        authorToUpdate.setFirstname("Carson");
        authorToUpdate.setLastname("Young");

        // Act
        Author updatedAuthor = authorService.updateAuthor(authorToUpdate);

        // Assert
        assertAll(
                () -> assertNotNull(updatedAuthor),
                () -> assertNotEquals("Decker", updatedAuthor.getLastname()),
                () -> assertEquals("Young", updatedAuthor.getLastname())
        );
    }

    @Test
    void deleteAuthor() throws Exception {
        // Arrange
        AuthorDeleteDTO authorToDelete = new AuthorDeleteDTO();
        authorToDelete.setId(1L);

        // Act
        authorService.deleteAuthor(authorToDelete);

        // Assert
        assertAll(
                () -> assertEquals(2, authorService.getAllAuthors().size())
        );

    }

    @Test
    void getAuthorById() throws Exception {

        // Act
       Author author = authorService.getAuthorById(2L);

       // Assert
        assertAll(
                () -> assertNotNull(author),
                () -> assertEquals("Wood", author.getLastname())
        );

    }

    public static void createDummyData() throws Exception{
        AuthorInsertDTO a1 = new AuthorInsertDTO();
        a1.setFirstname("Aspen");
        a1.setLastname("Clark");

        authorService.insertAuthor(a1);

        AuthorInsertDTO a2 = new AuthorInsertDTO();
        a2.setFirstname("Amaia");
        a2.setLastname("Wood");

        authorService.insertAuthor(a2);

        AuthorInsertDTO a3 = new AuthorInsertDTO();
        a3.setFirstname("Kody");
        a3.setLastname("Decker");

        authorService.insertAuthor(a3);
    }

}