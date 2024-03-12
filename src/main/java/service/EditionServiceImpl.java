package service;

import dao.EditionDAOImpl;
import dao.IGenericDAO;
import dao.util.JPAHelper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ext.Provider;
import model.Edition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.dto.delete.EditionDeleteDTO;
import service.dto.insert.EditionInsertDTO;
import service.dto.update.EditionUpdateDTO;
import service.exceptions.EntityNotFoundException;
import service.util.mapper.EditionMapper;

import java.util.List;

/**
 * The {@link EditionServiceImpl} class implements the
 * CRUD wrapper methods as defined in the {@link IEditionService}
 * interface.
 */
@Provider
@ApplicationScoped
public class EditionServiceImpl implements IEditionService{

    private static final Logger logger = LoggerFactory.getLogger(EditionServiceImpl.class);
    private final IGenericDAO<Edition> editionDAO = new EditionDAOImpl();

    @Override
    public Edition insertEdition(EditionInsertDTO dto) throws Exception {
        Edition edition;
        Edition insertedEdition;

        try {
            JPAHelper.beginTransaction();

            edition = EditionMapper.mapToEdition(dto);
            insertedEdition = editionDAO.insert(edition);
            if(insertedEdition.getId() == null) {
                throw new Exception("Insert error");
            }

            JPAHelper.commitTransaction();
            logger.info("Edition with id " + insertedEdition.getId() + " inserted");

        } catch(Exception e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Edition not inserted");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

        return insertedEdition;
    }

    @Override
    public Edition updateEdition(EditionUpdateDTO dto) throws EntityNotFoundException {
        Edition editionToUpdate;
        Edition updatedEdition;

        try {
            JPAHelper.beginTransaction();
            if(editionDAO.getById(dto.getId()) == null) {
                throw new EntityNotFoundException(Edition.class, dto.getId());
            }
            editionToUpdate = EditionMapper.mapToEdition(dto);
            updatedEdition = editionDAO.update(editionToUpdate);

            JPAHelper.commitTransaction();
            logger.info("Edition with id " + updatedEdition + " updated");

        } catch(EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Edition with id " + dto.getId() + " not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

        return updatedEdition;
    }

    @Override
    public void deleteEdition(EditionDeleteDTO dto) throws EntityNotFoundException {
        Edition editionToDelete;

        try {
            JPAHelper.beginTransaction();

            if(editionDAO.getById(dto.getId()) == null) {
                throw new EntityNotFoundException(Edition.class, dto.getId());
            }
            editionToDelete = EditionMapper.mapToEdition(dto);
            editionDAO.delete(editionToDelete.getId());

            JPAHelper.commitTransaction();
            logger.info("Edition with id " + editionToDelete.getId() + " deleted");
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Edition with id " + dto.getId() + " not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
    }

    @Override
    public List<Edition> getEditionsByFormat(String format) throws EntityNotFoundException {
        List<Edition> editions;
        try {
            JPAHelper.beginTransaction();
            editions = editionDAO.getByKeyword(format);
            if(editions.isEmpty()) {
                throw new EntityNotFoundException(List.class, 0L);
            }

            JPAHelper.commitTransaction();
            logger.info("Editions with format " + format + " found");

        } catch(EntityNotFoundException e){
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Editions with format " + format + " not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return editions;
    }

    @Override
    public Edition getEditionById(Long id) throws EntityNotFoundException {
        Edition edition;

        try {
            JPAHelper.beginTransaction();

            edition = editionDAO.getById(id);
            if(edition == null) {
                throw new EntityNotFoundException(Edition.class, id);
            }

            JPAHelper.commitTransaction();
            logger.info("Edition with id " + id + " found");

        } catch(EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Edition with id " + id + " not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

        return edition;
    }

    @Override
    public List<Edition> getAllEditions() throws Exception {
        List<Edition> editions;

        try {
            JPAHelper.beginTransaction();

            editions = editionDAO.getAll();
            if(editions.isEmpty()) {
                throw new Exception("Editions retrieval error");
            }

            JPAHelper.commitTransaction();
            logger.info("Books were found");
        } catch(Exception e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Books were not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

        return editions;
    }
}
