package dao;

import dao.util.DatabaseHelper;
import dao.util.JPAHelper;
import model.Edition;
import model.enumfields.FormatType;
import model.enumfields.LanguageType;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class EditionDAOTest {
    private static IGenericDAO<Edition> editionDAO;
    private static LocalDate date;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        editionDAO = new EditionDAOImpl();
    }

    @BeforeEach
    void setUp() {
        createdDummyData();
    }

    @AfterEach
    void tearDown() {
        JPAHelper.getEntityManager().clear();
        DatabaseHelper.eraseData();
    }

    // Tests

    @Test
    void insertEdition() {
        JPAHelper.beginTransaction();

        // Arrange
        date = LocalDate.parse("01-Jan-1999", DateTimeFormatter.ofPattern("d-MMM-yyyy"));
        Edition ed3 = new Edition();
        ed3.setFormat(FormatType.PAPERBACK);
        ed3.setPublicationDate(date);
        ed3.setLanguage(LanguageType.ENGLISH);
        ed3.setPageCount(233);

        // Act
        editionDAO.insert(ed3);

        // Assert
        assertEquals(3, editionDAO.getAll().size());

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();
    }

    @Test
    void getAndUpdateEdition() {
        JPAHelper.beginTransaction();

        // Arrange
        date = LocalDate.parse("17-Apr-2009", DateTimeFormatter.ofPattern("d-MMM-yyyy"));
        Edition editionToUpdate = editionDAO.getById(1L);
        editionToUpdate.setFormat(FormatType.EBOOK);
        editionToUpdate.setPublicationDate(date);
        editionToUpdate.setLanguage(LanguageType.ENGLISH);
        editionToUpdate.setPageCount(273);

        // Act
        editionDAO.update(editionToUpdate);

        // Assert
        assertAll(
                () -> assertNotNull(editionDAO.getById(1L)),
                () -> assertEquals(FormatType.EBOOK, editionDAO.getById(1L).getFormat())
        );

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();
    }

    @Test
    void deleteEdition() {
        JPAHelper.beginTransaction();

        // Act
        editionDAO.delete(1L);

        // Assert
        assertNull(editionDAO.getById(1L));

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();
    }

    @Test
    void getEditionByFormat() {
        JPAHelper.beginTransaction();

        // Assert
        assertEquals(2, editionDAO.getByKeyword(FormatType.PAPERBACK.toString()).size());

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();
    }

    public static void createdDummyData() {
        JPAHelper.beginTransaction();

        date = LocalDate.parse("01-Jan-1868", DateTimeFormatter.ofPattern("d-MMM-yyyy"));
        Edition ed1 = new Edition();
        ed1.setFormat(FormatType.PAPERBACK);
        ed1.setPublicationDate(date);
        ed1.setLanguage(LanguageType.ENGLISH);
        ed1.setPageCount(449);
        editionDAO.insert(ed1);

        date = LocalDate.parse("20-Sep-2001", DateTimeFormatter.ofPattern("d-MMM-yyyy"));
        Edition ed2 = new Edition();
        ed2.setFormat(FormatType.PAPERBACK);
        ed2.setPublicationDate(date);
        ed2.setLanguage(LanguageType.ENGLISH);
        ed2.setPageCount(351);
        editionDAO.insert(ed2);

        JPAHelper.commitTransaction();
        JPAHelper.closeEntityManager();
    }

}