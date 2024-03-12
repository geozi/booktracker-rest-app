package service.dto.insert;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import service.dto.BaseDTO;

/**
 * The {@link RepositoryInsertDTO} class is the DTO equivalent of the
 * {@link model.Repository} class specifically adjusted to insert operations.
 */
@NoArgsConstructor
@Getter
@Setter
public class RepositoryInsertDTO {
    @NotNull
    @Size(min = 2, message = "Repository name must be at least 2 characters long")
    private String name;
    @NotNull
    @Size(min = 6, message = "Repository url must be at least 6 characters long")
    private String url;

    public RepositoryInsertDTO(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
