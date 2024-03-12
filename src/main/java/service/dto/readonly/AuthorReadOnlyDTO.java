package service.dto.readonly;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import service.dto.BaseDTO;

/**
 * The {@link AuthorReadOnlyDTO} class is the DTO equivalent of the
 * {@link model.Author} class specifically adjusted to read-only operations.
 * It inherits from the {@link BaseDTO} class.
 */
@NoArgsConstructor
@Getter
@Setter
public class AuthorReadOnlyDTO extends BaseDTO {
    @NotNull
    @Size(min = 2, max = 45, message = "Author firstname must be between 2 and 45 characters")
    private String firstname;
    @NotNull
    @Size(min = 2, max = 45, message = "Author lastname must be between 2 and 45 characters")
    private String lastname;

    public AuthorReadOnlyDTO(Long id) {
        super.setId(id);
    }

    public AuthorReadOnlyDTO(Long id, String firstname, String lastname) {
        super.setId(id);
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
