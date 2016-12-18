package database.service;

import database.dao.DAO;
import org.hibernate.SessionFactory;

public class DAOFactory {
    private static DAOFactory instance = null;
    private static SessionFactory sessionFactory = null;

    public static synchronized DAOFactory getInstance(SessionFactory sessionFactory) {
        if (instance == null) {
            instance = new DAOFactory(sessionFactory);
        }
        return instance;
    }

    private DAOFactory(SessionFactory sessionFactory) {
        DAOFactory.sessionFactory = sessionFactory;
    }
    
    public DAO getDaoBuClass(Class clazz) {
        return new DAO(sessionFactory, clazz);
    }

}
