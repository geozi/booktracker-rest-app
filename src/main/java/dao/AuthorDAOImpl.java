package dao;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import jakarta.ws.rs.ext.Provider;
import model.Author;

import java.util.List;

/**
 * The {@link AuthorDAOImpl} class implements the
 * CRUD operations for the Author primary entity
 * as defined in the {@link IGenericDAO} interface.
 * It inherits a functionality implementation from the
 * {@link AbstractEntityDAO} class.
 */
@Provider
@ApplicationScoped
public class AuthorDAOImpl extends AbstractEntityDAO implements IGenericDAO<Author> {

    @Override
    public Author insert(Author author) {
        EntityManager em = getEntityManager();
        em.persist(author);
        return author;
    }

    @Override
    public Author update(Author author) {
        EntityManager em = getEntityManager();
        em.merge(author);
        return author;
    }

    @Override
    public void delete(Long id) {
        EntityManager em = getEntityManager();
        Author authorToDelete = em.find(Author.class, id);
        em.remove(authorToDelete);
    }

    @Override
    public List<Author> getByKeyword(String lastname) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Author> selectQuery = builder.createQuery(Author.class);
        Root<Author> root = selectQuery.from(Author.class);

        ParameterExpression<String> paramLastname = builder.parameter(String.class);
        selectQuery.select(root).where(builder.like(root.get("lastname"), paramLastname));
        return getEntityManager()
                .createQuery(selectQuery)
                .setParameter(paramLastname, lastname + "%")
                .getResultList();
    }

    @Override
    public List<Author> getAll() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Author> selectQuery = builder.createQuery(Author.class);
        Root<Author> root = selectQuery.from(Author.class);
        selectQuery.select(root);

        return getEntityManager().createQuery(selectQuery).getResultList();
    }

    @Override
    public Author getById(Long id) {
        return getEntityManager().find(Author.class, id);
    }

}
