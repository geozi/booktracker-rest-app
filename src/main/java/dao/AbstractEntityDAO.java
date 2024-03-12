package dao;

import dao.util.JPAHelper;
import jakarta.persistence.EntityManager;

/**
 * The {@link AbstractEntityDAO} class provides an implementation
 * of a functionality that is common to its subclasses.
 */
public abstract class AbstractEntityDAO {
    protected EntityManager getEntityManager() {
        return JPAHelper.getEntityManager();
    }
}
