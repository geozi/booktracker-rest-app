package service.dto.delete;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Book;
import model.Publisher;
import model.enumfields.FormatType;
import model.enumfields.LanguageType;
import service.dto.BaseDTO;

import java.time.LocalDate;

/**
 * The {@link EditionDeleteDTO} class is the DTO equivalent of the
 * {@link model.Edition} class specifically adjusted to delete operations.
 * It inherits from the {@link BaseDTO} class.
 */
@NoArgsConstructor
@Getter
@Setter
public class EditionDeleteDTO extends BaseDTO {

    private FormatType format;
    private LocalDate publicationDate;
    @Min(value = 50, message = "Page count must be between 50 and 2000 pages")
    @Max(value = 2000, message = "Page count must be between 50 and 2000 pages")
    @Digits(integer = 4, fraction = 0)
    private Integer pageCount;
    private LanguageType language;
    private Publisher publisher;
    private Book book;

    public EditionDeleteDTO(Long id) {
        this.setId(id);
    }

    public EditionDeleteDTO(Long id, FormatType format, LocalDate publicationDate, Integer pageCount,
                            LanguageType language, Publisher publisher, Book book) {
        this.setId(id);
        this.format = format;
        this.publicationDate = publicationDate;
        this.pageCount = pageCount;
        this.language = language;
        this.publisher = publisher;
        this.book = book;
    }
}
