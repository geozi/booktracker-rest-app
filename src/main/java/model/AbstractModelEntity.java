package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * The {@link AbstractModelEntity} class is the super-class of
 * all domain model classes, offering common functionality.
 */
@MappedSuperclass
@Getter
@Setter
public abstract class AbstractModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
}
