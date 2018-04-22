package ru.asmirnov.market.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asmirnov.market.db.dao.ItemDao;
import ru.asmirnov.market.db.entity.Item;
import ru.asmirnov.market.service.ItemService;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
@Service
@SuppressWarnings("unused")
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;

    @Autowired
    public ItemServiceImpl(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Transactional(readOnly = true)
    public Item findAvailableByCode(String code) {
        try {
            return itemDao.findAvailableByCode(code);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Item> findAvailable() {
        return itemDao.findAvailable();
    }
}
