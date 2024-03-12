package service.dto.insert;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import service.dto.BaseDTO;

/**
 * The {@link AuthorInsertDTO} class is the DTO equivalent of the
 * {@link model.Author} class specifically adjusted to insert operations.
 */
@NoArgsConstructor
@Getter
@Setter
public class AuthorInsertDTO {
    @NotNull
    @Size(min = 2, max = 45, message = "Author firstname must be between 2 and 45 characters long." )
    private String firstname;
    @NotNull
    @Size(min = 2, max = 45, message = "Author lastname must be between 2 and 45 characters long.")
    private String lastname;

    public AuthorInsertDTO(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
