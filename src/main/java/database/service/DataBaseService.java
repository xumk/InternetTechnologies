/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.service;

import database.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Алексей
 */
public class DataBaseService {

    private static final String HIBERNATE_SHOW_SQL = "false";
    private static final String HIBERNATE_HBM2DDL_AUTO = "update";
    private static final String HIBERNATE_DIALECT = "org.hibernate.dialect.SQLiteDialect";
    private static final String HIBERNATE_CONNECTION_DRIVER = "org.sqlite.JDBC";
    private final String CONNECTION_URL = "jdbc:sqlite:C:/database/users.db";
    private static final String DATABASE_USERNAME = "";
    private static final String DATABASE_PASSWORD = "";
    private final SessionFactory sessionFactory;
    private static DataBaseService service;

    private DataBaseService() {
        Configuration configuration = this.getPostgreSqlConfiguration();
        this.sessionFactory = createSessionFactory(configuration);
    }

    public static synchronized DataBaseService instanceDataBaseService() {
        if (service == null) {
            service = new DataBaseService();
        }
        return service;
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    private Configuration getPostgreSqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", HIBERNATE_DIALECT);
        configuration.setProperty("hibernate.connection.driver_class", HIBERNATE_CONNECTION_DRIVER);
        configuration.setProperty("hibernate.connection.url", CONNECTION_URL);
        configuration.setProperty("hibernate.connection.username", DATABASE_USERNAME);
        configuration.setProperty("hibernate.connection.password", DATABASE_PASSWORD);
        configuration.setProperty("hibernate.show_sql", HIBERNATE_SHOW_SQL);
        configuration.setProperty("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
        configuration.setProperty("format_sql", "true");
        return configuration;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        StandardServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public void closeSessionFactory() {
        this.sessionFactory.close();
    }
}
