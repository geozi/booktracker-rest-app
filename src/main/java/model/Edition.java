package model;

import jakarta.persistence.*;
import lombok.*;
import model.enumfields.FormatType;
import model.enumfields.LanguageType;

import java.time.LocalDate;

/**
 * The {@link Edition} class is an abstract representation
 * of a book edition. It maps to an Edition primary entity in the database
 * and inherits from the {@link AbstractModelEntity} class.
 */
@Entity
@Table(name = "Editions", indexes = {
        @Index(name = "Format_idx", columnList = "Format"),
        @Index(name = "Language_idx", columnList = "Language")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Edition extends AbstractModelEntity {
    @Column(name = "Format")
    @Enumerated(EnumType.STRING)
    private FormatType format;
    @Column(name = "Publication_Date")
    private LocalDate publicationDate;
    @Column(name = "Page_Count")
    private Integer pageCount;
    @Column(name = "Language")
    @Enumerated(EnumType.STRING)
    private LanguageType language;

    public Edition(Long id, FormatType format, LocalDate publicationDate, Integer pageCount, LanguageType language, Publisher publisher, Book book) {
        this.setId(id);
        this.format = format;
        this.publicationDate = publicationDate;
        this.pageCount = pageCount;
        this.language = language;
        this.publisher = publisher;
        this.book = book;
    }

    // Relations

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Publisher_ID", referencedColumnName = "id")
    private Publisher publisher;

    @ManyToOne(fetch = FetchType.EAGER) // For testing purposes
    @JoinColumn(name = "Book_ID", referencedColumnName = "id")
    private Book book;

}
