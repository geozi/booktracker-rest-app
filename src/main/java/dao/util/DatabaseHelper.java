package dao.util;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;


public class DatabaseHelper  {

    /**
     * No instances of this Utility class should be available.
     */
    private DatabaseHelper() {

    }

    /**
     * Remove all data from database.
     * The functionality is executed within the bounds of a transaction
     */
    public static void  eraseData() {
        EntityManager em = JPAHelper.getEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // Disable FK
        em.createNativeQuery("SET @@foreign_key_checks=0").executeUpdate();

        // Find all tables from public schema and truncate them
        List<String> tables = em.createNativeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES  where TABLE_SCHEMA='bookDB'").getResultList();
        for (String table : tables) {
            em.createNativeQuery("TRUNCATE TABLE " + table).executeUpdate();
        }

        // Enable FK
        em.createNativeQuery("SET @@foreign_key_checks=1").executeUpdate();

        tx.commit();

        em.close();
    }

}