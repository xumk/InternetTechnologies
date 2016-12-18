/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Transaction;

/**
 *
 * @author Алексей
 */
public class DAO<T> {

    protected SessionFactory sessionFactory;
    protected Class clazz;

    public DAO(SessionFactory sessionFactory, Class clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }
    
    public T getEntityByStringProperty(String properties, String value) {
        Session session = null;
        T obj = null;
        try {
            session = this.sessionFactory.openSession();
            Criteria e = session.createCriteria(clazz);
            obj = (T) e.add(Restrictions.eq(properties, value)).uniqueResult();
        } catch (Exception ex) {
           
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return obj;
    }
    
    public void addObject(T object) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            Transaction e = session.beginTransaction();
            session.save(object);
            e.commit();
        } catch (Exception ex) {
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
