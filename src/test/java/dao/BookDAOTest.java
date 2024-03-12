package dao;

import dao.util.DatabaseHelper;
import dao.util.JPAHelper;
import model.Book;
import model.enumfields.GenreType;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BookDAOTest {
    private static IGenericDAO<Book> bookDAO ;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        bookDAO = new BookDAOImpl();
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
    void insertBook() {
        JPAHelper.beginTransaction();

        // Arrange
        Book bk4 = new Book();
        bk4.setTitle("Life of Pi");
        bk4.setIsbn("978-0770430078");
        bk4.setGenre(GenreType.FICTION);

        // Act
        bookDAO.insert(bk4);

        // Assert
        assertEquals(4, bookDAO.getAll().size());

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();
    }

    @Test
    void getAndUpdateBook() {
        JPAHelper.beginTransaction();

        // Arrange
        Book bookToUpdate = bookDAO.getById(1L);
        bookToUpdate.setTitle("Little Women");
        bookToUpdate.setIsbn("978-0451529305");
        bookToUpdate.setGenre(GenreType.FICTION);

        // Act
        bookDAO.update(bookToUpdate);

        // Assert
        assertAll(
                () -> assertNotNull(bookDAO.getById(1L)),
                () -> assertEquals("Little Women", bookDAO.getById(1L).getTitle())
        );

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();
    }
    @Test
    void deleteBook() {
        JPAHelper.beginTransaction();

        // Act
        bookDAO.delete(3L);

        // Assert
        assertNull(bookDAO.getById(3L));

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();
    }

    @Test
    void getBookByTitle() {
        JPAHelper.beginTransaction();

        // Assert
        assertEquals(1, bookDAO.getByKeyword("The Help").size());

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();
    }

    public static void createDummyData() {
        JPAHelper.beginTransaction();

        Book bk1 = new Book();
        bk1.setTitle("Graceling");
        bk1.setIsbn("978-0152063962");
        bk1.setGenre(GenreType.FICTION);
        bookDAO.insert(bk1);

        Book bk2 = new Book();
        bk2.setTitle("The Help");
        bk2.setIsbn("978-0399155345");
        bk2.setGenre(GenreType.FICTION);
        bookDAO.insert(bk2);

        Book bk3 = new Book();
        bk3.setTitle("My Sister's Keeper");
        bk2.setIsbn("978-0743454537");
        bk2.setGenre(GenreType.FICTION);
        bookDAO.insert(bk3);

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();
    }
}