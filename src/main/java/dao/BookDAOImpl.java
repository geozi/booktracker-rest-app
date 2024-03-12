package dao;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import jakarta.ws.rs.ext.Provider;
import model.Book;

import java.util.List;

/**
 * The {@link BookDAOImpl} class implements the
 * CRUD operations for the Book primary entity
 * as defined in the {@link IGenericDAO} interface.
 * It inherits a functionality implementation from the
 * {@link AbstractEntityDAO} class.
 */
@Provider
@ApplicationScoped
public class BookDAOImpl extends AbstractEntityDAO implements IGenericDAO<Book> {

    @Override
    public Book insert(Book book) {
        EntityManager em = getEntityManager();
        em.persist(book);
        return book;
    }

    @Override
    public Book update(Book book) {
        EntityManager em = getEntityManager();
        em.merge(book);
        return book;
    }

    @Override
    public void delete(Long id) {
        EntityManager em = getEntityManager();
        Book bookToDelete = em.find(Book.class, id);
        em.remove(bookToDelete);
    }

    @Override
    public List<Book> getByKeyword(String title) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Book> selectQuery = builder.createQuery(Book.class);
        Root<Book> root = selectQuery.from(Book.class);

        ParameterExpression<String> paramTitle = builder.parameter(String.class);
        selectQuery.select(root).where(builder.like(root.get("title"), paramTitle));
        return getEntityManager()
                .createQuery(selectQuery)
                .setParameter(paramTitle, title + "%")
                .getResultList();
    }

    @Override
    public List<Book> getAll() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Book> selectQuery = builder.createQuery(Book.class);
        Root<Book> root = selectQuery.from(Book.class);
        selectQuery.select(root);

        return getEntityManager().createQuery(selectQuery).getResultList();
    }

    @Override
    public Book getById(Long id) {
        return getEntityManager().find(Book.class, id);
    }

}

