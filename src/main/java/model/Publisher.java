package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * The {@link Publisher} class is an abstract representation
 * of a book's publisher. It maps to a Publisher primary entity in the database
 * and inherits from the {@link AbstractModelEntity} class.
 */
@Entity
@Table(name = "Publishers", indexes = {
        @Index(name = "Name_idx", columnList = "Name"),
        @Index(name = "City_idx", columnList = "City")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Publisher extends AbstractModelEntity {
    @Column(name = "Name", length = 45)
    private String name;
    @Column(name = "Phone_Number", length = 20)
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name ="Street_Address", length = 100)
    private String streetAddress;
    @Column(name = "City")
    private String city;
    @Column(name = "Url")
    private String url;

    public Publisher(String name, String phoneNumber, String email, String streetAddress, String city, String url) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.streetAddress = streetAddress;
        this.city = city;
        this.url = url;
    }

    public Publisher(Long id, String name, String phoneNumber, String email, String streetAddress, String city, String url) {
        this.setId(id);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.streetAddress = streetAddress;
        this.city = city;
        this.url = url;
    }

    // Relations

    @OneToMany(mappedBy = "publisher")
    @Getter(AccessLevel.PROTECTED)
    private Set <Edition> editions = new HashSet<>();

    // Convenient methods

    public Set<Edition> getAllEditions() {
        return Collections.unmodifiableSet(editions);
    }

    public void addEdition(Edition edition){
        editions.add(edition);
        edition.setPublisher(this);
    }
}
