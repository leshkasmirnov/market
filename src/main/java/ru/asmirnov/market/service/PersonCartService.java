package ru.asmirnov.market.service;

import ru.asmirnov.market.db.entity.Person;
import ru.asmirnov.market.db.entity.PersonCart;

import java.util.List;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
public interface PersonCartService {

    /**
     * Put items to person's cart.
     *
     * @param personCarts
     */
    void put(List<PersonCart> personCarts);

    /**
     * Checkout items from person's cart.
     *
     */
    List<PersonCart> checkout(Person person);
}
