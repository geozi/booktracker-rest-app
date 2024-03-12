package service.dto;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * The {@link BaseDTO} class enables subtype polymorphism for
 * DTO implementations.
 */
@Getter
@Setter
public abstract class BaseDTO {
    @NotNull
    private Long id;
}
