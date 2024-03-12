package dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import jakarta.ws.rs.ext.Provider;
import model.Repository;

import java.util.List;

/**
 * The {@link RepositoryDAOImpl} class implements the
 * CRUD operations for the Repository primary entity
 * as defined in the {@link IGenericDAO} interface.
 * It inherits a functionality implementation from the
 * {@link AbstractEntityDAO} class.
 */
@Provider
@ApplicationScoped
public class RepositoryDAOImpl extends AbstractEntityDAO implements IGenericDAO<Repository> {

    @Override
    public Repository insert(Repository repo) {
        EntityManager em = getEntityManager();
        em.persist(repo);
        return repo;
    }

    @Override
    public Repository update(Repository repo) {
        EntityManager em = getEntityManager();
        em.merge(repo);
        return repo;
    }

    @Override
    public void delete(Long id) {
        EntityManager em = getEntityManager();
        Repository repoToDelete = em.find(Repository.class, id);
        em.remove(repoToDelete);
    }

    @Override
    public List<Repository> getByKeyword(String name) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Repository> selectQuery = builder.createQuery(Repository.class);
        Root<Repository> root = selectQuery.from(Repository.class);

        ParameterExpression<String> paramName = builder.parameter(String.class);
        selectQuery.select(root).where(builder.like(root.get("name"), paramName));
        return getEntityManager()
                .createQuery(selectQuery)
                .setParameter(paramName, name + "%")
                .getResultList();
    }


    @Override
    public List<Repository> getAll() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Repository> selectQuery = builder.createQuery(Repository.class);
        Root<Repository> root = selectQuery.from(Repository.class);
        selectQuery.select(root);

        return getEntityManager().createQuery(selectQuery).getResultList();
    }

    @Override
    public Repository getById(Long id) {
        return getEntityManager().find(Repository.class, id);
    }
}
