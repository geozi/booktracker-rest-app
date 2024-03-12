package service.dto.delete;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import service.dto.BaseDTO;

/**
 * The {@link AuthorDeleteDTO} class is the DTO equivalent of the
 * {@link model.Author} class specifically adjusted to delete operations.
 * It inherits from the {@link BaseDTO} class.
 */
@NoArgsConstructor
@Getter
@Setter
public class AuthorDeleteDTO extends BaseDTO {
    @Size(min = 2, max = 45, message = "Author firstname must be between 2 and 45 characters")
    private String firstname;
    @Size(min = 2, max = 45, message = "Author lastname must be between 2 and 45 characters")
    private String lastname;

    public AuthorDeleteDTO(Long id) {
        this.setId(id);
    }

    public AuthorDeleteDTO(Long id, String firstname, String lastname) {
        this.setId(id);
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
