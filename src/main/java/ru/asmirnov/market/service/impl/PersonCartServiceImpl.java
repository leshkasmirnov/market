package ru.asmirnov.market.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asmirnov.market.db.dao.PersonCartDao;
import ru.asmirnov.market.db.entity.Person;
import ru.asmirnov.market.db.entity.PersonCart;
import ru.asmirnov.market.service.PersonCartService;

import java.util.List;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
@Service
@SuppressWarnings("unused")
public class PersonCartServiceImpl implements PersonCartService {

    private final PersonCartDao personCartDao;

    public PersonCartServiceImpl(PersonCartDao personCartDao) {
        this.personCartDao = personCartDao;
    }

    @Transactional
    public void put(List<PersonCart> personCarts) {
        personCarts.forEach(personCart -> {
            if (personCart.getCount() > personCart.getItem().getAvailableQuantity()) {
                throw new RuntimeException("For item " + personCart.getItem().getName() + " available count less than requested");
            }
            personCartDao.create(personCart);
        });
    }

    @Transactional(readOnly = true)
    public List<PersonCart> checkout(Person person) {
        return personCartDao.findByPerson(person);
    }
}
