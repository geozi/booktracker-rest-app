package service.dto.delete;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import service.dto.BaseDTO;

/**
 * The {@link RepositoryDeleteDTO} class is the DTO equivalent of the
 * {@link model.Repository} class specifically adjusted to delete operations.
 * It inherits from the {@link BaseDTO} class.
 */
@NoArgsConstructor
@Getter
@Setter
public class RepositoryDeleteDTO extends BaseDTO {
    @Size(min = 2, message = "Repository name must be at least 2 characters long")
    private String name;
    @Size(min = 6, message = "Repository url must be at least 6 characters long")
    private String url;

    public RepositoryDeleteDTO(Long id) {
        this.setId(id);
    }

    public RepositoryDeleteDTO(Long id, String name, String url) {
        this.setId(id);
        this.name = name;
        this.url = url;
    }
}
