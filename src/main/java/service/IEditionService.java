package service;

import model.Edition;
import service.dto.delete.EditionDeleteDTO;
import service.dto.insert.EditionInsertDTO;
import service.dto.update.EditionUpdateDTO;
import service.exceptions.EntityNotFoundException;

import java.util.List;

/**
 * The {@link IEditionService} interface contains the
 * Service-layer definitions of the CRUD wrapper methods
 * for the Edition primary entity.
 * It inherits from the {@link IGenericService}
 */
public interface IEditionService extends IGenericService {

    Edition insertEdition(EditionInsertDTO dto) throws Exception;

    Edition updateEdition(EditionUpdateDTO dto) throws EntityNotFoundException;

    void deleteEdition(EditionDeleteDTO dto) throws EntityNotFoundException;

    List<Edition> getEditionsByFormat(String format) throws EntityNotFoundException;

    List<Edition> getAllEditions() throws Exception;

    Edition getEditionById(Long id) throws EntityNotFoundException;
}
