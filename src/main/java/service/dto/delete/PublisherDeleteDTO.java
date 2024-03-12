package service.dto.delete;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import service.dto.BaseDTO;

/**
 * The {@link PublisherDeleteDTO} class is the DTO equivalent of the
 * {@link model.Publisher} class specifically adjusted to delete operations.
 * It inherits from the {@link BaseDTO} class.
 */
@NoArgsConstructor
@Getter
@Setter
public class PublisherDeleteDTO extends BaseDTO {

    @Size(min = 2, max = 45, message = "Publisher name must be between 2 and 45 characters in length")
    private String name;
    @Size(min = 2, max = 20, message = "Publisher phone number must be between 2 and 20 characters in length")
    private String phoneNumber;
    @Size(min = 2, max = 45, message = "Publisher email must be between 2 and 45 characters in length")
    private String email;
    @Size(min = 2, max = 100, message = "Publisher street address must be between 2 and 100 characters in length")
    private String streetAddress;
    @Size(min = 2, max = 45, message = "Publisher city must be between 2 and 45 characters in length")
    private String city;
    @Size(min = 6, message = "Publisher url must be at least 6 characters long")
    private String url;

    public PublisherDeleteDTO(Long id) {
        this.setId(id);
    }

    public PublisherDeleteDTO(Long id, String name, String phoneNumber, String email,
                              String streetAddress, String city, String url) {
        this.setId(id);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.streetAddress = streetAddress;
        this.city = city;
        this.url = url;
    }
}
