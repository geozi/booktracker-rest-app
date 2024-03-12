package service.dto.update;

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
 * The {@link EditionUpdateDTO} class is the DTO equivalent of the
 * {@link model.Edition} class specifically adjusted to update operations.
 * It inherits from the {@link BaseDTO} class.
 */
@NoArgsConstructor
@Getter
@Setter
public class EditionUpdateDTO  extends BaseDTO {
    @NotNull
    private FormatType format;
    @NotNull
    private LocalDate publicationDate;
    @NotNull
    @Min(value = 50, message = "Page count must be between 50 and 2000 pages")
    @Max(value = 2000, message = "Page count must be between 50 and 2000 pages")
    @Digits(integer = 4, fraction = 0)
    private Integer pageCount;
    @NotNull
    private LanguageType language;
    @NotNull
    private Publisher publisher;
    @NotNull
    private Book book;

    public EditionUpdateDTO(Long id) {
        this.setId(id);
    }

    public EditionUpdateDTO(Long id, FormatType format, LocalDate publicationDate,
                            Integer pageCount, LanguageType language, Publisher publisher, Book book) {
        this.setId(id);
        this.format = format;
        this.publicationDate = publicationDate;
        this.pageCount = pageCount;
        this.language = language;
        this.publisher = publisher;
        this.book = book;
    }
}
