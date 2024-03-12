package service;

import dao.AuthorDAOImpl;
import dao.IGenericDAO;
import dao.util.JPAHelper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ext.Provider;
import model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.dto.delete.AuthorDeleteDTO;
import service.dto.insert.AuthorInsertDTO;
import service.dto.update.AuthorUpdateDTO;
import service.exceptions.EntityNotFoundException;
import service.util.mapper.AuthorMapper;

import java.util.List;

/**
 * The {@link AuthorServiceImpl} class implements the
 * CRUD wrapper methods as defined in the {@link IAuthorService}
 * interface.
 */
@Provider
@ApplicationScoped
public class AuthorServiceImpl implements IAuthorService{

    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);
    private final IGenericDAO<Author> authorDAO = new AuthorDAOImpl();

    @Override
    public Author insertAuthor(AuthorInsertDTO dto) throws Exception {
        Author author;
        Author insertedAuthor;
        try {
            JPAHelper.beginTransaction();

            author = AuthorMapper.mapToAuthor(dto);
            insertedAuthor = authorDAO.insert(author);
            if(insertedAuthor.getId() == null) {
                throw new Exception("Insert Error");
            }

            JPAHelper.commitTransaction();
            logger.info("Author with id " + author.getId() + " inserted");

        } catch (Exception e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Author not inserted");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

        return insertedAuthor;
    }

    @Override
    public Author updateAuthor(AuthorUpdateDTO dto) throws EntityNotFoundException {
        Author authorToUpdate;
        Author updatedAuthor;

        try {
            JPAHelper.beginTransaction();

            if(authorDAO.getById(dto.getId()) == null) {
                throw new EntityNotFoundException(Author.class, dto.getId());
            }
            authorToUpdate = AuthorMapper.mapToAuthor(dto);
            updatedAuthor  = authorDAO.update(authorToUpdate);

            JPAHelper.commitTransaction();
            logger.info("Author with id " + updatedAuthor.getId() + " updated");

        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Author with id " + dto.getId() + " not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

        return updatedAuthor;
    }

    @Override
    public void deleteAuthor(AuthorDeleteDTO dto) throws EntityNotFoundException {
        Author authorToDelete;
        try {
            JPAHelper.beginTransaction();

            if(authorDAO.getById(dto.getId()) == null) {
                throw new EntityNotFoundException(Author.class, dto.getId());
            }
            authorToDelete = AuthorMapper.mapToAuthor(dto);
            authorDAO.delete(authorToDelete.getId());

            JPAHelper.commitTransaction();
            logger.info("Author with id " + authorToDelete.getId() + " deleted");

        } catch(EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Author with id " + dto.getId() + " not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
    }

    @Override
    public List<Author> getAuthorsByLastname(String lastname) throws EntityNotFoundException {
        List<Author> authors;

        try {
            JPAHelper.beginTransaction();
            authors = authorDAO.getByKeyword(lastname);
            if(authors.isEmpty()) {
                throw new EntityNotFoundException(List.class, 0L);
            }
            JPAHelper.commitTransaction();
            logger.info("Authors with lastname " + lastname + " found");

        } catch(EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Authors with " + lastname + " not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

        return authors;
    }

    @Override
    public Author getAuthorById(Long id) throws EntityNotFoundException {
        Author author;

        try {
            JPAHelper.beginTransaction();
            author = authorDAO.getById(id);
            if(author == null) {
                throw new EntityNotFoundException(Author.class, id);
            }
            JPAHelper.commitTransaction();
            logger.info("Author with id " + id + " found");

        } catch(EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Author with id " + id + " not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

        return author;
    }

    @Override
    public List<Author> getAllAuthors() throws Exception {
        List<Author> authors;

        try {
            JPAHelper.beginTransaction();
            authors = authorDAO.getAll();
            if(authors.isEmpty()) {
                throw new Exception("Authors retrieval error");
            }
            JPAHelper.commitTransaction();
            logger.info("Authors were found");
        } catch (Exception e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: No Authors were found");
            throw e;
        }finally {
            JPAHelper.closeEntityManager();
        }

        return authors;
    }
}
