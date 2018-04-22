package ru.asmirnov.market.db.dao.common;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDao<T extends Serializable> extends AbstractDao<T> {

    @Autowired
    protected SessionFactory sessionFactory;

    public T findOne(final long id) {
        return getCurrentSession().get(entityClass, id);
    }

    @Transactional
    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + entityClass.getName()).getResultList();
    }

    public void create(final T entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public T update(final T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
