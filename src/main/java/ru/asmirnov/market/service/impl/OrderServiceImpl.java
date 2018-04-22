package ru.asmirnov.market.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asmirnov.market.common.PayMethod;
import ru.asmirnov.market.db.dao.OrderItemDao;
import ru.asmirnov.market.db.dao.ItemDao;
import ru.asmirnov.market.db.dao.PersonCartDao;
import ru.asmirnov.market.db.dao.PersonOrderDao;
import ru.asmirnov.market.db.entity.Item;
import ru.asmirnov.market.db.entity.OrderItem;
import ru.asmirnov.market.db.entity.PersonCart;
import ru.asmirnov.market.db.entity.PersonOrder;
import ru.asmirnov.market.service.OrderService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
@Service
@SuppressWarnings("unused")
public class OrderServiceImpl implements OrderService {

    private final PersonOrderDao personOrderDao;
    private final OrderItemDao orderItemDao;
    private final ItemDao itemDao;
    private final PersonCartDao personCartDao;

    @Autowired
    public OrderServiceImpl(PersonOrderDao personOrderDao, OrderItemDao orderItemDao, ItemDao itemDao, PersonCartDao personCartDao) {
        this.personOrderDao = personOrderDao;
        this.orderItemDao = orderItemDao;
        this.itemDao = itemDao;
        this.personCartDao = personCartDao;
    }

    @Override
    @Transactional
    public PersonOrder createOrder(List<PersonCart> personCarts, String address, PayMethod payMethod) {
        PersonOrder result = new PersonOrder();
        result.setPerson(personCarts.get(0).getPerson());
        result.setAddress(address);
        result.setPayMethod(payMethod);

        Set<OrderItem> orderItemList = personCarts.stream()
                .map(personCart -> new OrderItem(result, personCart.getItem(), personCart.getCount()))
                .collect(Collectors.toSet());
        result.setItems(orderItemList);
        personCartDao.deleteByPerson(personCarts.get(0).getPerson());
        return result;
    }

    @Override
    @Transactional
    public void acceptOrder(PersonOrder personOrder) {
        personOrderDao.create(personOrder);
        personOrder.getItems().forEach(orderItem -> {
            orderItemDao.create(new OrderItem(personOrder, orderItem.getItem(), orderItem.getCount()));
            Item item = orderItem.getItem();
            item.setAvailableQuantity(item.getAvailableQuantity() - orderItem.getCount());
            itemDao.update(item);
        });
    }
}
