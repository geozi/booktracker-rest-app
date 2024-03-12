package dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import jakarta.ws.rs.ext.Provider;
import model.Publisher;

import java.util.List;

/**
 * The {@link PublisherDAOImpl} class implements the
 * CRUD operations for the Publisher primary entity
 * as defined in the {@link IGenericDAO} interface.
 * It inherits a functionality implementation from the
 * {@link AbstractEntityDAO} class.
 */
@Provider
@ApplicationScoped
public class PublisherDAOImpl extends AbstractEntityDAO implements IGenericDAO<Publisher> {

    @Override
    public Publisher insert(Publisher publisher) {
        EntityManager em = getEntityManager();
        em.persist(publisher);
        return publisher;
    }

    @Override
    public Publisher update(Publisher publisher) {
        EntityManager em = getEntityManager();
        em.merge(publisher);
        return publisher;
    }

    @Override
    public void delete(Long id) {
        EntityManager em = getEntityManager();
        Publisher publisherToBeDeleted = em.find(Publisher.class, id);
        em.remove(publisherToBeDeleted);
    }

    @Override
    public List<Publisher> getByKeyword(String name) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Publisher> selectQuery = builder.createQuery(Publisher.class);
        Root<Publisher> root = selectQuery.from(Publisher.class);

        ParameterExpression<String> paramName = builder.parameter(String.class);
        selectQuery.select(root).where(builder.like(root.get("name"), paramName));
        return getEntityManager()
                .createQuery(selectQuery)
                .setParameter(paramName, name + "%")
                .getResultList();
    }

    @Override
    public List<Publisher> getAll() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Publisher> selectQuery = builder.createQuery(Publisher.class);
        Root<Publisher> root = selectQuery.from(Publisher.class);
        selectQuery.select(root);

        return getEntityManager().createQuery(selectQuery).getResultList();
    }

    @Override
    public Publisher getById(Long id) {
        return getEntityManager().find(Publisher.class, id);
    }
}
