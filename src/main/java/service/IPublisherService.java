package service;

import model.Publisher;
import service.dto.delete.PublisherDeleteDTO;
import service.dto.insert.PublisherInsertDTO;
import service.dto.update.PublisherUpdateDTO;
import service.exceptions.EntityNotFoundException;

import java.util.List;

/**
 * The {@link IPublisherService} interface contains the
 * Service-layer definitions of the CRUD wrapper methods
 * for the Publisher primary entity.
 * It inherits from the {@link IGenericService}
 */
public interface IPublisherService extends IGenericService {

    Publisher insertPublisher(PublisherInsertDTO dto) throws Exception;

    Publisher updatePublisher(PublisherUpdateDTO dto) throws EntityNotFoundException;

    void deletePublisher(PublisherDeleteDTO dto)  throws EntityNotFoundException;

    List<Publisher> getPublishersByName(String name) throws EntityNotFoundException;

    List<Publisher> getAllPublishers() throws Exception;

    Publisher getPublisherById(Long id) throws EntityNotFoundException;
}
