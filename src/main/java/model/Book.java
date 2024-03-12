package model;

import jakarta.persistence.*;
import lombok.*;
import model.enumfields.GenreType;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * The {@link Book} class is an abstract representation
 * of a book. It maps to a Book primary entity in the database
 * and inherits from the {@link AbstractModelEntity} class.
 */
@Entity
@Table(name = "Books",
        indexes = {
        @Index(name = "Title_idx", columnList = "Title"),
        @Index(name = "Genre_idx", columnList = "Genre")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book extends AbstractModelEntity {

    @Column(name = "Title")
    private String title;
    @Column(name = "Genre", length = 20)
    @Enumerated(EnumType.STRING)
    private GenreType genre;
    @Column(name = "Isbn")
    private String isbn;

    public Book(String title, GenreType genre, String isbn) {
        this.title = title;
        this.genre = genre;
        this.isbn = isbn;
    }

    public Book(Long id, String title, GenreType genre, String isbn) {
        this.setId(id);
        this.title = title;
        this.genre = genre;
        this.isbn = isbn;
    }

    // Relations

    @ManyToMany
    @Getter(AccessLevel.PROTECTED)
    @JoinTable(
            name = "Books_Authors",
            joinColumns = @JoinColumn(name = "Book_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "Author_ID", referencedColumnName = "id")
    )
    private Set<Author> authors = new HashSet<>();

    @OneToMany(mappedBy = "book")
    @Getter(AccessLevel.PROTECTED)
    private Set<Edition> editions = new HashSet<>();

    @ManyToMany
    @Getter(AccessLevel.PROTECTED)
    @JoinTable(
            name = "Books_Repositories",
            joinColumns = @JoinColumn(name = "Book_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "Repository_ID", referencedColumnName = "id")
    )
    private Set<Repository> repos = new HashSet<>();

    // Convenient methods

    public Set<Edition> getAllEditions() {
        return Collections.unmodifiableSet(editions);
    }

    public void addEdition(Edition edition){
        editions.add(edition);
        edition.setBook(this);
    }

    public Set<Author> getAllAuthors() {
        return Collections.unmodifiableSet(authors);
    }

    public void addAuthor(Author author) {
        authors.add(author);
        author.getBooks().add(this);
    }

    public Set <Repository> getAllRepositories() {
        return Collections.unmodifiableSet(repos);
    }

    public void addRepository(Repository repo) {
        repos.add(repo);
        repo.getBooks().add(this);
    }
}
