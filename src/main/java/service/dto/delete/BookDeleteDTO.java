package service.dto.delete;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.enumfields.GenreType;
import service.dto.BaseDTO;

/**
 * The {@link BookDeleteDTO} class is the DTO equivalent of the
 * {@link model.Book} class specifically adjusted to delete operations.
 * It inherits from the {@link BaseDTO} class.
 */
@NoArgsConstructor
@Getter
@Setter
public class BookDeleteDTO extends BaseDTO {
    @Size(min = 2, message = "Book title must be at least 2 characters long")
    private String title;
    private GenreType genre;
    @Size(min = 14, max = 14, message = "Book ISBN must be 14 characters long (13 + hyphen)")
    private String isbn;

    public BookDeleteDTO(Long id) {
        this.setId(id);
    }

    public BookDeleteDTO(Long id, String title, GenreType genre, String isbn) {
        this.setId(id);
        this.title = title;
        this.genre = genre;
        this.isbn = isbn;
    }
}
