package ru.asmirnov.market.db.dao.impl;

import org.springframework.stereotype.Repository;
import ru.asmirnov.market.db.dao.PersonOrderDao;
import ru.asmirnov.market.db.dao.common.AbstractHibernateDao;
import ru.asmirnov.market.db.entity.PersonOrder;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
@Repository
@SuppressWarnings("unused")
public class PersonOrderHibernateDaoImpl extends AbstractHibernateDao<PersonOrder> implements PersonOrderDao {

    public PersonOrderHibernateDaoImpl() {
        setEntityClass(PersonOrder.class);
    }
}
