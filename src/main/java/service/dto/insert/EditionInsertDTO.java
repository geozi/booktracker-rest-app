package service.dto.insert;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
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
 * The {@link EditionInsertDTO} class is the DTO equivalent of the
 * {@link model.Edition} class specifically adjusted to insert operations.
 */
@NoArgsConstructor
@Getter
@Setter
public class EditionInsertDTO {
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

    public EditionInsertDTO(FormatType format, LocalDate publicationDate,
                            Integer pageCount, LanguageType language, Publisher publisher, Book book) {
        this.format = format;
        this.publicationDate = publicationDate;
        this.pageCount = pageCount;
        this.language = language;
        this.publisher = publisher;
        this.book = book;
    }
}
