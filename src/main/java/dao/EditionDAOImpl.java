package dao;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import jakarta.ws.rs.ext.Provider;

import model.Edition;
import model.enumfields.FormatType;


import java.util.ArrayList;
import java.util.List;

/**
 * The {@link EditionDAOImpl} class implements the
 * CRUD operations for the Edition primary entity
 * as defined in the {@link IGenericDAO} interface.
 * It inherits a functionality implementation from the
 * {@link AbstractEntityDAO} class.
 */
@Provider
@ApplicationScoped
public class EditionDAOImpl extends AbstractEntityDAO implements IGenericDAO<Edition> {

    @Override
    public Edition insert(Edition edition) {
        EntityManager em = getEntityManager();
        em.persist(edition);
        return edition;
    }

    @Override
    public Edition update(Edition edition) {
        EntityManager em = getEntityManager();
        em.merge(edition);
        return edition;
    }

    @Override
    public void delete(Long id) {
        EntityManager em = getEntityManager();
        Edition editionToDelete = em.find(Edition.class, id);
        em.remove(editionToDelete);
    }

    @Override
    public List<Edition> getByKeyword(String format) {

        List<Edition> editionLst = new ArrayList<>();

        for(FormatType val : FormatType.values()) {
            if(val.toString().equals(format.toUpperCase())){

                CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
                CriteriaQuery<Edition> selectQuery = builder.createQuery(Edition.class);
                Root<Edition> root = selectQuery.from(Edition.class);

                ParameterExpression<FormatType> paramFormat = builder.parameter(FormatType.class);
                selectQuery.select(root).where(builder.equal(root.get("format"), paramFormat));
                editionLst = getEntityManager()
                        .createQuery(selectQuery)
                        .setParameter(paramFormat, val )
                        .getResultList();
            }
        }

        return editionLst;

    }

    @Override
    public List<Edition> getAll() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Edition> selectQuery = builder.createQuery(Edition.class);
        Root<Edition> root = selectQuery.from(Edition.class);
        selectQuery.select(root);

        return getEntityManager().createQuery(selectQuery).getResultList();
    }

    @Override
    public Edition getById(Long id) {
        return getEntityManager().find(Edition.class, id);
    }

}
