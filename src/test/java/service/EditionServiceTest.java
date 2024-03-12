package service;

import dao.util.DatabaseHelper;
import dao.util.JPAHelper;
import model.Book;
import model.Edition;
import model.Publisher;
import model.enumfields.FormatType;
import model.enumfields.GenreType;
import model.enumfields.LanguageType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.dto.delete.EditionDeleteDTO;
import service.dto.insert.BookInsertDTO;
import service.dto.insert.EditionInsertDTO;
import service.dto.insert.PublisherInsertDTO;
import service.dto.update.EditionUpdateDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class EditionServiceTest {
    private static IEditionService editionService;
    private static IBookService bookService;
    private static IPublisherService publisherService;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        editionService = new EditionServiceImpl();
        bookService = new BookServiceImpl();
        publisherService = new PublisherServiceImpl();
    }

    @BeforeEach
    void setUp() throws Exception {
        createdDummyData();
    }

    @AfterEach
    void tearDown() {
        JPAHelper.getEntityManager().clear();
        DatabaseHelper.eraseData();
    }

    // Tests

    @Test
    void insertEdition() throws Exception{

        // Arrange
        BookInsertDTO book = new BookInsertDTO();
        book.setTitle("The Three Musketeers");
        book.setGenre(GenreType.FICTION);
        book.setIsbn("978-1505234725");
        Book insertedBook = bookService.insertBook(book);

        PublisherInsertDTO pub = new PublisherInsertDTO();
        pub.setName("CreateSpace");
        pub.setPhoneNumber("(206) 266-4064.");
        pub.setEmail("contact@createspace.com");
        pub.setStreetAddress("4900 Lacross Rd");
        pub.setCity("North Charleston");
        pub.setUrl("https://www.createspace.com/");
        Publisher insertedPublisher = publisherService.insertPublisher(pub);

        LocalDate date = LocalDate.parse("05-Dec-2014", DateTimeFormatter.ofPattern("d-MMM-yyyy"));
        EditionInsertDTO e3 = new EditionInsertDTO();
        e3.setFormat(FormatType.PAPERBACK);
        e3.setLanguage(LanguageType.ENGLISH);
        e3.setPageCount(398);
        e3.setPublicationDate(date);
        e3.setBook(insertedBook);
        e3.setPublisher(insertedPublisher);

        // Act
        Edition insertedEdition = editionService.insertEdition(e3);

        // Assert
        assertAll(
                () -> assertNotNull(insertedEdition),
                () -> assertEquals("CreateSpace", insertedEdition.getPublisher().getName()),
                () -> assertEquals("The Three Musketeers", insertedEdition.getBook().getTitle()),
                () -> assertEquals(3, editionService.getAllEditions().size())
        );

    }

    @Test
    void updateEdition() throws Exception {
        // Arrange
        EditionUpdateDTO editionToUpdate = new EditionUpdateDTO();
        editionToUpdate.setId(1L);
        editionToUpdate.setFormat(FormatType.EBOOK);

        // Act
        Edition updatedEdition = editionService.updateEdition(editionToUpdate);

        // Assert
        assertAll(
                () -> assertNotNull(updatedEdition),
                () -> assertNotEquals(FormatType.PAPERBACK, updatedEdition.getFormat()),
                () -> assertEquals(FormatType.EBOOK, updatedEdition.getFormat())
        );


    }

    @Test
    void deleteEdition() throws Exception {
        // Arrange
        EditionDeleteDTO editionToDelete = new EditionDeleteDTO();
        editionToDelete.setId(1L);

        // Act
        editionService.deleteEdition(editionToDelete);

        // Assert
        assertEquals(1, editionService.getAllEditions().size());
    }

    @Test
    void getEditionById() throws Exception {
        // Act
        Edition edition = editionService.getEditionById(2L);

        // Assert
        assertAll(
                () -> assertNotNull(edition),
                () -> assertEquals("The Hobbit", editionService.getEditionById(2L).getBook().getTitle()),
                () -> assertEquals(366, editionService.getEditionById(2L).getPageCount())
        );
    }


    public static void createdDummyData() throws Exception {
        // Edition 1
        BookInsertDTO bk1 = new BookInsertDTO();
        bk1.setTitle("A Tale of Two Cities");
        bk1.setGenre(GenreType.FICTION);
        bk1.setIsbn("978-0141439600");
        Book insertedBook = bookService.insertBook(bk1);

        PublisherInsertDTO pb1 = new PublisherInsertDTO();
        pb1.setName("Penguin Books");
        pb1.setPhoneNumber("+442070628940");
        pb1.setEmail("consumerservices@penguinrandomhouse.com");
        pb1.setStreetAddress("Embassy Gardens");
        pb1.setCity("London");
        pb1.setUrl("https://www.penguin.co.uk/");
        Publisher insertedPublisher = publisherService.insertPublisher(pb1);

        LocalDate date = LocalDate.parse("01-Jan-2003", DateTimeFormatter.ofPattern("d-MMM-yyyy"));
        EditionInsertDTO e1 = new EditionInsertDTO();
        e1.setFormat(FormatType.PAPERBACK);
        e1.setLanguage(LanguageType.ENGLISH);
        e1.setPageCount(489);
        e1.setPublicationDate(date);
        e1.setBook(insertedBook);
        e1.setPublisher(insertedPublisher);

        editionService.insertEdition(e1);

        // Edition 2
        BookInsertDTO bk2 = new BookInsertDTO();
        bk2.setTitle("The Hobbit");
        bk2.setGenre(GenreType.FICTION);
        bk2.setIsbn(" 978-0547928227 ");
        insertedBook = bookService.insertBook(bk2);

        PublisherInsertDTO pb2 = new PublisherInsertDTO();
        pb2.setName("Houghton Mifflin");
        pb2.setPhoneNumber("800-323-9239");
        pb2.setEmail("techsupport@hmhco.com");
        pb2.setStreetAddress("125 High St");
        pb2.setCity("Boston");
        pb2.setUrl("https://www.hmhco.com/");

        insertedPublisher = publisherService.insertPublisher(pb2);

        date = LocalDate.parse("15-Aug-2002", DateTimeFormatter.ofPattern("d-MMM-yyyy"));
        EditionInsertDTO e2 = new EditionInsertDTO();
        e2.setFormat(FormatType.PAPERBACK);
        e2.setLanguage(LanguageType.ENGLISH);
        e2.setPageCount(366);
        e2.setPublicationDate(date);
        e2.setBook(insertedBook);
        e2.setPublisher(insertedPublisher);

        editionService.insertEdition(e2);

    }
}