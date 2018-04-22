package ru.asmirnov.market.db.dao.impl;

import org.springframework.stereotype.Repository;
import ru.asmirnov.market.db.dao.OrderItemDao;
import ru.asmirnov.market.db.dao.common.AbstractHibernateDao;
import ru.asmirnov.market.db.entity.OrderItem;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
@Repository
@SuppressWarnings("unused")
public class OrderItemHibernateDaoImpl extends AbstractHibernateDao<OrderItem> implements OrderItemDao {

    public OrderItemHibernateDaoImpl() {
        setEntityClass(OrderItem.class);
    }
}
