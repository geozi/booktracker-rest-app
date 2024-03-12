package service.dto.update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.enumfields.GenreType;
import service.dto.BaseDTO;

/**
 * The {@link BookUpdateDTO} class is the DTO equivalent of the
 * {@link model.Book} class specifically adjusted to update operations.
 * It inherits from the {@link BaseDTO} class.
 */
@NoArgsConstructor
@Getter
@Setter
public class BookUpdateDTO  extends BaseDTO {
    @NotNull
    @Size(min = 2, message = "Book title must be at least 2 characters long")
    private String title;
    @NotNull
    private GenreType genre;
    @NotNull
    @Size(min = 14, max = 14, message = "Book ISBN must be 14 characters long (13 + hyphen)")
    private String isbn;

    public BookUpdateDTO(Long id) {
        this.setId(id);
    }

    public BookUpdateDTO(Long id, String title, GenreType genre, String isbn) {
        this.setId(id);
        this.title = title;
        this.genre = genre;
        this.isbn = isbn;
    }
}
