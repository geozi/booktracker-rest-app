package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * The {@link Author} class is an abstract representation
 * of a book author. It maps to an Author primary entity in
 * the database and inherits from the {@link AbstractModelEntity} class.
 */
@Entity
@Table(name = "Authors", indexes = {
        @Index(name = "Lastname_idx", columnList = "Lastname")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Author extends AbstractModelEntity {
    @Column(name  = "Firstname", length = 45)
    private String firstname;
    @Column(name = "Lastname", length = 45)
    private String lastname;

    public Author(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Author(Long id, String firstname, String lastname) {
        this.setId(id);
        this.firstname = firstname;
        this.lastname = lastname;
    }

    // Relations

    @ManyToMany
    @Getter(AccessLevel.PROTECTED)
    @JoinTable(
            name = "Authors_Books",
            joinColumns = @JoinColumn(name = "Author_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "Book_ID", referencedColumnName = "id")
    )
    private Set<Book> books = new HashSet<>();

    // Convenient methods

    public void addBook(Book book) {
        books.add(book);
        book.getAuthors().add(this);
    }

    public Set<Book> getAllBooks() {
        return Collections.unmodifiableSet(books);
    }

}
