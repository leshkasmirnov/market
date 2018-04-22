package ru.asmirnov.market.db.dao.impl;

import org.springframework.stereotype.Repository;
import ru.asmirnov.market.db.dao.PersonCartDao;
import ru.asmirnov.market.db.dao.common.AbstractHibernateDao;
import ru.asmirnov.market.db.entity.Person;
import ru.asmirnov.market.db.entity.PersonCart;

import java.util.List;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
@Repository
@SuppressWarnings("unused")
public class PersonCartHibernateDaoImpl extends AbstractHibernateDao<PersonCart> implements PersonCartDao {

    public PersonCartHibernateDaoImpl() {
        setEntityClass(PersonCart.class);
    }

    public List<PersonCart> findByPerson(Person person) {
        return getCurrentSession()
                .createQuery("from PersonCart where person = :person", PersonCart.class)
                .setParameter("person", person)
                .getResultList();
    }

    public void deleteByPerson(Person person) {
        getCurrentSession()
                .createQuery("delete from PersonCart where person.id = :personId")
                .setParameter("personId", person.getId())
                .executeUpdate();
    }

    @Override
    public PersonCart findOne(long id) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public void deleteById(long entityId) {
        throw new UnsupportedOperationException("This operation is not supported");
    }
}
