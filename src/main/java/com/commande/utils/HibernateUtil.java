package com.commande.utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {
    private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Charger la configuration Hibernate
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml"); // Assurez-vous que ce fichier est dans src/main/resources
            sessionFactory = configuration.buildSessionFactory();
            logger.info("Hibernate SessionFactory initialized successfully.");
        } catch (HibernateException ex) {
            // Journaliser et arrêter l'initialisation en cas d'erreur
            logger.error("Initial SessionFactory creation failed: {}", ex.getMessage(), ex);
            throw new ExceptionInInitializerError("Hibernate configuration error. Check stack trace for details.", ex);
        }
    }

    /**
     * Retourne la SessionFactory Hibernate pour la gestion des sessions.
     *
     * @return La SessionFactory Hibernate.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Ferme la SessionFactory lorsque l'application se termine.
     */
    public static void shutdown() {
        if (sessionFactory != null) {
            logger.info("Shutting down Hibernate SessionFactory.");
            sessionFactory.close();
        }
    }
}
