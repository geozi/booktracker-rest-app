package service.util.mapper;

import model.Repository;
import service.dto.delete.RepositoryDeleteDTO;
import service.dto.insert.RepositoryInsertDTO;
import service.dto.readonly.RepositoryReadOnlyDTO;
import service.dto.update.RepositoryUpdateDTO;

/**
 * The {@link RepositoryMapper} class is a utility class that maps DTOs to
 * the Repository model class.
 */
public class RepositoryMapper {

    private RepositoryMapper() {}

    public static Repository mapToRepository(RepositoryInsertDTO dto) {
        return new Repository(dto.getName(), dto.getUrl());
    }
    public static Repository mapToRepository(RepositoryUpdateDTO dto) {
        return new Repository(dto.getId(), dto.getName(), dto.getUrl());
    }
    public static Repository mapToRepository(RepositoryDeleteDTO dto) {
        return new Repository(dto.getId(), dto.getName(), dto.getUrl());
    }
    public static RepositoryReadOnlyDTO mapToRepository(Repository repo) {
        return new RepositoryReadOnlyDTO(repo.getId(), repo.getName(), repo.getUrl());
    }
}
