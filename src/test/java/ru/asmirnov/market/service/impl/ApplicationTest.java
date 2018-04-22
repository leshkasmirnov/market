package ru.asmirnov.market.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import ru.asmirnov.market.common.PayMethod;
import ru.asmirnov.market.config.ApplicationConfig;
import ru.asmirnov.market.db.dao.ItemDao;
import ru.asmirnov.market.db.dao.PersonOrderDao;
import ru.asmirnov.market.db.entity.Item;
import ru.asmirnov.market.db.entity.Person;
import ru.asmirnov.market.db.entity.PersonCart;
import ru.asmirnov.market.db.entity.PersonOrder;
import ru.asmirnov.market.service.ItemService;
import ru.asmirnov.market.service.OrderService;
import ru.asmirnov.market.service.PersonCartService;
import ru.asmirnov.market.service.PersonService;

import java.util.Collections;
import java.util.List;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
@ContextConfiguration(classes = ApplicationConfig.class)
public class ApplicationTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private PersonService personService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private PersonCartService personCartService;
    @Autowired
    private OrderService orderService;

    @Autowired
    private PersonOrderDao personOrderDao;

    @Autowired
    private ItemDao itemDao;

    @Test
    public void test() {
        Person person = personService.findById(1L);
        Item ma001 = itemService.findAvailableByCode("MA001");
        personCartService.put(Collections.singletonList(new PersonCart(person, ma001, 10L)));

        // some time ago...
        List<PersonCart> checkout = personCartService.checkout(person);
        PersonOrder order = orderService.createOrder(checkout, "address", PayMethod.CASH);
        orderService.acceptOrder(order);

        System.out.println(personOrderDao.findAll());
        System.out.println(itemDao.findAll());
    }
}
