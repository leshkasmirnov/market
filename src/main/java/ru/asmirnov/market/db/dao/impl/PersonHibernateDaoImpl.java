package ru.asmirnov.market.db.dao.impl;

import org.springframework.stereotype.Repository;
import ru.asmirnov.market.db.dao.PersonDao;
import ru.asmirnov.market.db.dao.common.AbstractHibernateDao;
import ru.asmirnov.market.db.entity.Person;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
@Repository
@SuppressWarnings("unused")
public class PersonHibernateDaoImpl extends AbstractHibernateDao<Person> implements PersonDao {

    public PersonHibernateDaoImpl() {
        setEntityClass(Person.class);
    }
}
