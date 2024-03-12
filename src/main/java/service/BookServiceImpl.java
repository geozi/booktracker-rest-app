package service;

import dao.BookDAOImpl;
import dao.IGenericDAO;
import dao.util.JPAHelper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ext.Provider;
import model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.dto.delete.BookDeleteDTO;
import service.dto.insert.BookInsertDTO;
import service.dto.update.BookUpdateDTO;
import service.exceptions.EntityNotFoundException;
import service.util.mapper.BookMapper;

import java.util.List;

/**
 * The {@link BookServiceImpl} class implements the
 * CRUD wrapper methods as defined in the {@link IBookService}
 * interface.
 */
@Provider
@ApplicationScoped
public class BookServiceImpl implements IBookService{

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    private final IGenericDAO<Book> bookDAO = new BookDAOImpl();

    @Override
    public Book insertBook(BookInsertDTO dto) throws Exception {
        Book book;
        Book insertedBook;
        try {
            JPAHelper.beginTransaction();

            book = BookMapper.mapToBook(dto);
            insertedBook = bookDAO.insert(book);
            if(insertedBook == null) {
                throw new Exception("Insert error");
            }

            JPAHelper.commitTransaction();
            logger.info("Book with id " + book.getId() + " inserted");
        } catch (Exception e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Book not inserted");
            throw e;
        } finally  {
            JPAHelper.closeEntityManager();
        }
        return insertedBook;
    }

    @Override
    public Book updateBook(BookUpdateDTO dto) throws EntityNotFoundException {
        Book bookToUpdate;
        Book updatedBook;

        try {
            JPAHelper.beginTransaction();

            if(bookDAO.getById(dto.getId()) == null) {
                throw new EntityNotFoundException(Book.class, dto.getId());
            }
            bookToUpdate = BookMapper.mapToBook(dto);
            updatedBook = bookDAO.update(bookToUpdate);

            JPAHelper.commitTransaction();
            logger.info("Book with id " + updatedBook.getId() + " updated");

        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Book with id " + dto.getId() + " not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return updatedBook;
    }

    @Override
    public void deleteBook(BookDeleteDTO dto) throws EntityNotFoundException {
        Book bookToDelete;
        try {
            JPAHelper.beginTransaction();
            if(bookDAO.getById(dto.getId()) == null) {
                throw new EntityNotFoundException(Book.class, dto.getId());
            }
            bookToDelete = BookMapper.mapToBook(dto);
            bookDAO.delete(bookToDelete.getId());

            JPAHelper.commitTransaction();
            logger.info("Book to with id " + bookToDelete.getId() + " deleted");

        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Book with id " + dto.getId() + " not found" );
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

    }

    @Override
    public List<Book> getBooksByTitle(String title) throws EntityNotFoundException {

        List<Book> books;
        try {
            JPAHelper.beginTransaction();
            books = bookDAO.getByKeyword(title);
            if(books.isEmpty()) {
                throw new EntityNotFoundException(List.class, 0L);
            }

            JPAHelper.commitTransaction();
            logger.info("Books with title " + title + " found");
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Books with title " + title + " not found");
            throw e;
        } finally  {
            JPAHelper.closeEntityManager();
        }
        return books;
    }

    @Override
    public Book getBookById(Long id) throws EntityNotFoundException {
        Book book;

        try {
            JPAHelper.beginTransaction();
            book = bookDAO.getById(id);
            if(book == null) {
                throw new EntityNotFoundException(Book.class, id);
            }

            JPAHelper.commitTransaction();
            logger.info("Book with id " + id + " found");

        } catch( EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Book with id " + id + " not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return book;
    }

    @Override
    public List<Book> getAllBooks() throws Exception {
        List<Book> books;

        try {
            JPAHelper.beginTransaction();

            books = bookDAO.getAll();
            if(books.isEmpty()){
                throw new Exception("Books retrieval error");
            }

            JPAHelper.commitTransaction();
            logger.info("Books were found");
        } catch(Exception e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Books were not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

        return books;
    }
}
