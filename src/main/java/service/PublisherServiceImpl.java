package service;

import dao.IGenericDAO;
import dao.PublisherDAOImpl;
import dao.util.JPAHelper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ext.Provider;
import model.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.dto.delete.PublisherDeleteDTO;
import service.dto.insert.PublisherInsertDTO;
import service.dto.update.PublisherUpdateDTO;
import service.exceptions.EntityNotFoundException;
import service.util.mapper.PublisherMapper;

import java.util.List;

/**
 * The {@link PublisherServiceImpl} class implements the
 * CRUD wrapper methods as defined in the {@link IPublisherService}
 * interface.
 */
@Provider
@ApplicationScoped
public class PublisherServiceImpl implements IPublisherService {

    private static final Logger logger = LoggerFactory.getLogger(PublisherServiceImpl.class);
    private final IGenericDAO<Publisher> publisherDAO = new PublisherDAOImpl();

    @Override
    public Publisher insertPublisher(PublisherInsertDTO dto) throws Exception {
        Publisher publisher;
        Publisher insertedPublisher;
        try {
            JPAHelper.beginTransaction();

            publisher = PublisherMapper.mapToPublisher(dto);
            insertedPublisher = publisherDAO.insert(publisher);
            if(insertedPublisher.getId() == null) {
                throw new Exception("Insert Error");
            }
            JPAHelper.commitTransaction();
            logger.info("Publisher with id " + publisher.getId() + " inserted");

        } catch(Exception e) {
             JPAHelper.rollbackTransaction();
             logger.warn("Rollback: Publisher not inserted");
             throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return insertedPublisher;
    }

    @Override
    public Publisher updatePublisher(PublisherUpdateDTO dto) throws EntityNotFoundException {
        Publisher publisherToUpdate;
        Publisher updatedPublisher;

        try{
            JPAHelper.beginTransaction();

            if(publisherDAO.getById(dto.getId()) == null) {
                throw new EntityNotFoundException(Publisher.class, dto.getId());
            }
            publisherToUpdate = PublisherMapper.mapToPublisher(dto);
            updatedPublisher = publisherDAO.update(publisherToUpdate);

            JPAHelper.commitTransaction();
            logger.info("Publisher with id " + updatedPublisher.getId() + " updated");
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Publisher with id " + dto.getId() + " not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

        return updatedPublisher;
    }

    @Override
    public void deletePublisher(PublisherDeleteDTO dto) throws EntityNotFoundException {
        Publisher publisherToDelete;

        try {
            JPAHelper.beginTransaction();

            if(publisherDAO.getById(dto.getId()) == null) {
                throw new EntityNotFoundException(Publisher.class, dto.getId());
            }
            publisherToDelete = PublisherMapper.mapToPublisher(dto);
            publisherDAO.delete(publisherToDelete.getId());

            JPAHelper.commitTransaction();
            logger.info("Publisher with id " + publisherToDelete.getId() + " deleted");
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Publisher with id " + dto.getId() + " not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
    }

    @Override
    public List<Publisher> getPublishersByName(String name) throws EntityNotFoundException {
        List<Publisher> publishers;
        try {
            JPAHelper.beginTransaction();

            publishers = publisherDAO.getByKeyword(name);
            if(publishers.isEmpty()) {
                throw new EntityNotFoundException(List.class, 0L);
            }

            JPAHelper.commitTransaction();
            logger.info("Publishers with name " + name + " found");
        } catch(EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Publishers with name " + name + "not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return publishers;
    }

    @Override
    public Publisher getPublisherById(Long id) throws EntityNotFoundException {
        Publisher publisher;

        try {
            JPAHelper.beginTransaction();

            publisher = publisherDAO.getById(id);
            if(publisher == null) {
                throw new EntityNotFoundException(Publisher.class, id);
            }

            JPAHelper.commitTransaction();
            logger.info("Publisher with id " + id + " found");
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Rollback: Publisher with id: " + id + " not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

        return publisher;
    }

    @Override
    public List<Publisher> getAllPublishers() throws Exception {
        List<Publisher> publishers;

        try {
            JPAHelper.beginTransaction();

            publishers = publisherDAO.getAll();
            if(publishers.isEmpty()) {
                throw new Exception("Publishers retrieval error");
            }

            JPAHelper.commitTransaction();
            logger.info("Publishers were found");

        } catch(Exception e) {
            JPAHelper.rollbackTransaction();
            logger.warn("Publishers were not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

        return publishers;
    }
}
