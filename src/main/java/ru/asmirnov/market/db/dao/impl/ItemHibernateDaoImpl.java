package ru.asmirnov.market.db.dao.impl;

import org.springframework.stereotype.Repository;
import ru.asmirnov.market.db.dao.ItemDao;
import ru.asmirnov.market.db.dao.common.AbstractHibernateDao;
import ru.asmirnov.market.db.entity.Item;

import java.util.List;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
@Repository
@SuppressWarnings("unused")
public class ItemHibernateDaoImpl extends AbstractHibernateDao<Item> implements ItemDao {

    public ItemHibernateDaoImpl() {
        setEntityClass(Item.class);
    }

    public Item findAvailableByCode(String code) {
        return getCurrentSession()
                .createQuery("from Item where code = :code and availableQuantity > 0", Item.class)
                .setParameter("code", code)
                .getSingleResult();
    }

    @Override
    public List<Item> findAvailable() {
        return getCurrentSession()
                .createQuery("from Item where availableQuantity > 0", Item.class)
                .getResultList();
    }
}
