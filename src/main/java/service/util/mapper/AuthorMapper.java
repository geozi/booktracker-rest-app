package service.util.mapper;

import model.Author;
import service.dto.delete.AuthorDeleteDTO;
import service.dto.insert.AuthorInsertDTO;
import service.dto.readonly.AuthorReadOnlyDTO;
import service.dto.update.AuthorUpdateDTO;

/**
 * The {@link AuthorMapper} class is a utility class that maps DTOs to
 * the Author model class.
 */
public class AuthorMapper  {

    private AuthorMapper () {}

    public static Author mapToAuthor(AuthorInsertDTO dto) {
        return new Author(dto.getFirstname(), dto.getLastname());
    }

    public static Author mapToAuthor(AuthorUpdateDTO dto) {
        return new Author(dto.getId(), dto.getFirstname(), dto.getLastname());
    }
    public static Author mapToAuthor(AuthorDeleteDTO dto) {
        return new Author(dto.getId(), dto.getFirstname(), dto.getLastname());
    }
    public static AuthorReadOnlyDTO mapToReadOnlyDTO(Author author) {
        return new AuthorReadOnlyDTO(author.getId(), author.getFirstname() , author.getLastname());
    }

}
