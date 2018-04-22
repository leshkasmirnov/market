package ru.asmirnov.market.service;

import ru.asmirnov.market.db.entity.Item;

import java.util.List;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
public interface ItemService {

    /**
     * Find available item by code. If no found returns null.
     *
     * @param code
     * @return
     */
    Item findAvailableByCode(String code);

    List<Item> findAvailable();
}
