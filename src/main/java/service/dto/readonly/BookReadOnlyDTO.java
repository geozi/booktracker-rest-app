package service.dto.readonly;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.enumfields.GenreType;
import service.dto.BaseDTO;

/**
 * The {@link BookReadOnlyDTO} class is the DTO equivalent of the
 * {@link model.Book} class specifically adjusted to read-only operations.
 * It inherits from the {@link BaseDTO} class.
 */
@NoArgsConstructor
@Getter
@Setter
public class BookReadOnlyDTO extends BaseDTO {
    @NotNull
    @Size(min = 2, message = "Book title must be at least 2 characters long")
    private String title;
    @NotNull
    private GenreType genre;
    @NotNull
    @Size(min = 14, max = 14, message = "Book ISBN must be 14 characters long (13 + hyphen)")
    private String isbn;

    public BookReadOnlyDTO(Long id) {
        super.setId(id);
    }

    public BookReadOnlyDTO(Long id, String title, GenreType genre, String isbn) {
        super.setId(id);
        this.title = title;
        this.genre = genre;
        this.isbn = isbn;
    }
}
