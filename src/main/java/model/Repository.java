package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * The {@link Repository} class is an abstract representation
 * of an online book repository. It maps to a Repository primary entity in
 * the database and inherits from the {@link AbstractModelEntity} class.
 */
@Entity
@Table(name = "Repositories", indexes = {
        @Index(name = "Name_idx", columnList = "Name")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Repository extends AbstractModelEntity {
    @Column(name = "Name")
    private String name;
    @Column(name = "Url")
    private String url;

    public Repository(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Repository(Long id, String name, String url) {
        this.setId(id);
        this.name = name;
        this.url = url;
    }

    // Relations

    @ManyToMany
    @Getter(AccessLevel.PROTECTED)
    @JoinTable(
            name = "Repositories_Books",
            joinColumns = @JoinColumn(name = "Repository_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "Book_ID", referencedColumnName = "id")
    )
    private Set<Book> books = new HashSet<>();

    // Convenient methods

    public void addBook(Book book) {
        books.add(book);
        book.getRepos().add(this);
    }

    public Set<Book> getAllBooks() {
        return Collections.unmodifiableSet(books);
    }
}
