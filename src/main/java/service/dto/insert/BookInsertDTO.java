package service.dto.insert;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.enumfields.GenreType;
import service.dto.BaseDTO;

/**
 * The {@link BookInsertDTO} class is the DTO equivalent of the
 * {@link model.Book} class specifically adjusted to insert operations.
 */
@NoArgsConstructor
@Getter
@Setter
public class BookInsertDTO {
    @NotNull
    @Size(min = 2, message = "Book title must be at least 2 characters long")
    private String title;
    @NotNull
    private GenreType genre;
    @NotNull
    @Size(min = 14, max = 14, message = "Book ISBN must be 14 characters long (13 + hyphen)")
    private String isbn;

    public BookInsertDTO(String title, GenreType genre, String isbn) {
        this.title = title;
        this.genre = genre;
        this.isbn = isbn;
    }
}
