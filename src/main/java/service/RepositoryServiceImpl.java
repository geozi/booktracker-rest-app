package service;

import dao.IGenericDAO;
import dao.RepositoryDAOImpl;
import dao.util.JPAHelper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ext.Provider;
import model.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.dto.delete.RepositoryDeleteDTO;
import service.dto.insert.RepositoryInsertDTO;
import service.dto.update.RepositoryUpdateDTO;
import service.exceptions.EntityNotFoundException;
import service.util.mapper.RepositoryMapper;

import java.util.List;

/**
 * The {@link RepositoryServiceImpl} class implements the
 * CRUD wrapper methods as defined in the {@link IRepositoryService}
 * interface.
 */
@Provider
@ApplicationScoped
public class RepositoryServiceImpl implements IRepositoryService {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryServiceImpl.class);
    private final IGenericDAO<Repository> repoDAO = new RepositoryDAOImpl();

    @Override
    public Repository insertRepo(RepositoryInsertDTO dto) throws Exception {
        Repository repo;
        Repository insertedRepo;

        try {
            JPAHelper.beginTransaction();
            repo = RepositoryMapper.mapToRepository(dto);
            insertedRepo = repoDAO.insert(repo);
            if(insertedRepo.getId() == null) {
                throw new Exception("Insert error");
            }

            JPAHelper.commitTransaction();
            logger.info("Repository with id " + insertedRepo.getId() + " inserted");

        } catch (Exception e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Repository not inserted");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

        return insertedRepo;
    }

    @Override
    public Repository updateRepo(RepositoryUpdateDTO dto) throws EntityNotFoundException {
        Repository repoToUpdate;
        Repository updatedRepo;

        try {
            JPAHelper.beginTransaction();

            if(repoDAO.getById(dto.getId()) == null) {
                throw new EntityNotFoundException(Repository.class, dto.getId());
            }
            repoToUpdate = RepositoryMapper.mapToRepository(dto);
            updatedRepo = repoDAO.update(repoToUpdate);

            JPAHelper.commitTransaction();
            logger.info("Repository with id " + updatedRepo.getId() + " updated");
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Repository with id " + dto.getId() + " not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

        return updatedRepo;
    }

    @Override
    public void deleteRepo(RepositoryDeleteDTO dto) throws EntityNotFoundException {
        Repository repoToDelete;

        try {
            JPAHelper.beginTransaction();
            if(repoDAO.getById(dto.getId()) == null) {
                throw new EntityNotFoundException(Repository.class, dto.getId());
            }
            repoToDelete = RepositoryMapper.mapToRepository(dto);
            repoDAO.delete(repoToDelete.getId());

            JPAHelper.commitTransaction();
            logger.info("Repository with id " + repoToDelete.getId() + " deleted");
        } catch(EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Repository with id " + dto.getId() + " not found");
        } finally {
            JPAHelper.closeEntityManager();
        }

    }

    @Override
    public List<Repository> getReposByName(String name) throws EntityNotFoundException {
        List<Repository> repos;
        try {
            JPAHelper.beginTransaction();
            repos = repoDAO.getByKeyword(name);
            if(repos.isEmpty()) {
                throw new EntityNotFoundException(List.class, 0L);
            }

            JPAHelper.commitTransaction();
            logger.info("Repositories with name " + name + " found");

        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Repositories with name " + name + " not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return repos;
    }

    @Override
    public Repository getRepoById(Long id) throws EntityNotFoundException {
        Repository repo;

        try {
            JPAHelper.beginTransaction();

            repo = repoDAO.getById(id);
            if(repo == null) {
                throw new EntityNotFoundException(Repository.class, id);
            }

            JPAHelper.commitTransaction();
            logger.info("Repository with id " + id + " found");

        } catch( EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Repository with id " + id + " not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

        return repo;
    }

    @Override
    public List<Repository> getAllRepos() throws Exception {
        List<Repository> repos;

        try {
            JPAHelper.beginTransaction();

            repos = repoDAO.getAll();
            if(repos.isEmpty()) {
                throw new Exception("Repositories retrieval error");
            }
            JPAHelper.commitTransaction();
            logger.info("Repositories found");
        } catch(Exception e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Repositories not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

        return repos;
    }
}
