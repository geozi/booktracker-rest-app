package service.util.mapper;

import model.Edition;
import service.dto.delete.EditionDeleteDTO;
import service.dto.insert.EditionInsertDTO;
import service.dto.readonly.EditionReadOnlyDTO;
import service.dto.update.EditionUpdateDTO;

/**
 * The {@link EditionMapper} class is a utility class that maps DTOs to
 * the Edition model class.
 */
public class EditionMapper {

    private EditionMapper() {}

    public static Edition mapToEdition(EditionInsertDTO dto){
        return new Edition(dto.getFormat(), dto.getPublicationDate(),
                dto.getPageCount() , dto.getLanguage(), dto.getPublisher(), dto.getBook());
    }
    public static Edition mapToEdition(EditionUpdateDTO dto){
        return new Edition(dto.getId(), dto.getFormat(),
                dto.getPublicationDate(), dto.getPageCount(), dto.getLanguage(),dto.getPublisher(), dto.getBook());
    }
    public static Edition mapToEdition(EditionDeleteDTO dto){
        return new Edition(dto.getId(), dto.getFormat(),
                dto.getPublicationDate(), dto.getPageCount(), dto.getLanguage(),dto.getPublisher(), dto.getBook());
    }
    public static EditionReadOnlyDTO mapToReadOnlyDTO(Edition edition) {
        return new EditionReadOnlyDTO(edition.getId(), edition.getFormat(), edition.getPublicationDate(),
                edition.getPageCount(), edition.getLanguage(), edition.getPublisher(),edition.getBook());
    }
}
