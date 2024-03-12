package service;

import model.Author;
import service.dto.delete.AuthorDeleteDTO;
import service.dto.insert.AuthorInsertDTO;
import service.dto.update.AuthorUpdateDTO;
import service.exceptions.EntityNotFoundException;

import java.util.List;

/**
 * The {@link IAuthorService} interface contains the
 * Service-layer definitions of the CRUD wrapper methods
 * for the Author primary entity.
 * It inherits from the {@link IGenericService}
 */
public interface IAuthorService extends IGenericService {

    Author insertAuthor(AuthorInsertDTO dto) throws Exception;

    Author updateAuthor(AuthorUpdateDTO dto) throws EntityNotFoundException;

    void deleteAuthor(AuthorDeleteDTO dto) throws EntityNotFoundException;

    List<Author> getAuthorsByLastname(String lastname) throws EntityNotFoundException;

    List<Author> getAllAuthors() throws Exception;

    Author getAuthorById(Long id) throws EntityNotFoundException;
}
