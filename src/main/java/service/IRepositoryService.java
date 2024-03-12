package service;

import model.Repository;
import service.dto.delete.RepositoryDeleteDTO;
import service.dto.insert.RepositoryInsertDTO;
import service.dto.update.RepositoryUpdateDTO;
import service.exceptions.EntityNotFoundException;

import java.util.List;

/**
 * The {@link IRepositoryService} interface contains the
 * Service-layer definitions of the CRUD wrapper methods
 * for the Repository primary entity.
 * It inherits from the {@link IGenericService}
 */
public interface IRepositoryService extends IGenericService {

    Repository insertRepo(RepositoryInsertDTO dto) throws Exception;

    Repository updateRepo(RepositoryUpdateDTO dto) throws EntityNotFoundException;

    void deleteRepo(RepositoryDeleteDTO dto) throws EntityNotFoundException;

    List<Repository> getReposByName(String name) throws EntityNotFoundException;

    List<Repository> getAllRepos() throws Exception;

    Repository getRepoById(Long id) throws EntityNotFoundException;
}
