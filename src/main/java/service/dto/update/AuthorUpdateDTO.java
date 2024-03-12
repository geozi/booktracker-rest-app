package service.dto.update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import service.dto.BaseDTO;

/**
 * The {@link AuthorUpdateDTO} class is the DTO equivalent of the
 * {@link model.Author} class specifically adjusted to update operations.
 * It inherits from the {@link BaseDTO} class.
 */
@NoArgsConstructor
@Getter
@Setter
public class AuthorUpdateDTO  extends BaseDTO {
    @NotNull
    @Size(min = 2, max = 45, message = "Author firstname must be between 2 and 45 characters")
    private String firstname;
    @NotNull
    @Size(min = 2, max = 45, message = "Author lastname must be between 2 and 45 characters")
    private String lastname;

    public AuthorUpdateDTO(Long id) {
        this.setId(id);
    }

    public AuthorUpdateDTO(Long id, String firstname, String lastname) {
        this.setId(id);
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
