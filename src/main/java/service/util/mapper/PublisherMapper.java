package service.util.mapper;

import model.Publisher;
import service.dto.delete.PublisherDeleteDTO;
import service.dto.insert.PublisherInsertDTO;
import service.dto.readonly.PublisherReadOnlyDTO;
import service.dto.update.PublisherUpdateDTO;

/**
 * The {@link PublisherMapper} class is a utility class that maps DTOs to
 * the Publisher model class.
 */
public class PublisherMapper {

    private PublisherMapper() {}

    public static Publisher mapToPublisher(PublisherInsertDTO dto) {
        return new Publisher(dto.getName(), dto.getPhoneNumber(), dto.getEmail(), dto.getStreetAddress(), dto.getCity(), dto.getUrl());
    }
    public static Publisher mapToPublisher(PublisherUpdateDTO dto) {
        return new Publisher(dto.getId(), dto.getName(), dto.getPhoneNumber(), dto.getEmail(), dto.getStreetAddress(), dto.getCity(), dto.getUrl());
    }
    public static Publisher mapToPublisher(PublisherDeleteDTO dto) {
        return new Publisher(dto.getId(), dto.getName(), dto.getPhoneNumber(), dto.getEmail(), dto.getStreetAddress(), dto.getCity(), dto.getUrl());
    }
    public static PublisherReadOnlyDTO mapToReadOnlyDTO(Publisher publisher) {
        return new PublisherReadOnlyDTO(publisher.getId(), publisher.getName(),
                publisher.getPhoneNumber(), publisher.getPhoneNumber(), publisher.getStreetAddress(), publisher.getCity(), publisher.getUrl());
    }
}
