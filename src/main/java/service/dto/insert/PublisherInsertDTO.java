package service.dto.insert;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import service.dto.BaseDTO;

/**
 * The {@link PublisherInsertDTO} class is the DTO equivalent of the
 * {@link model.Publisher} class specifically adjusted to insert operations.
 */
@NoArgsConstructor
@Getter
@Setter
public class PublisherInsertDTO {
    @NotNull
    @Size(min = 2, max = 45, message = "Publisher name must be between 2 and 45 characters in length")
    private String name;
    @NotNull
    @Size(min = 2, max = 20, message = "Publisher phone number must be between 2 and 20 characters in length")
    private String phoneNumber;
    @NotNull
    @Size(min = 2, max = 45, message = "Publisher email must be between 2 and 45 characters in length")
    private String email;
    @NotNull
    @Size(min = 2, max = 100, message = "Publisher street address must be between 2 and 100 characters in length")
    private String streetAddress;
    @NotNull
    @Size(min = 2, max = 45, message = "Publisher city must be between 2 and 45 characters in length")
    private String city;
    @NotNull
    @Size(min = 6, message = "Publisher url must be at least 6 characters long")
    private String url;

    public PublisherInsertDTO(String name, String phoneNumber,
                              String email, String streetAddress, String city, String url) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.streetAddress = streetAddress;
        this.city = city;
        this.url = url;
    }
}
