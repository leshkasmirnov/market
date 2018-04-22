package ru.asmirnov.market.db.dao;

import ru.asmirnov.market.db.dao.common.CrudOperations;
import ru.asmirnov.market.db.entity.Item;

import java.util.List;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
public interface ItemDao extends CrudOperations<Item> {

    Item findAvailableByCode(String code);

    List<Item> findAvailable();
}
