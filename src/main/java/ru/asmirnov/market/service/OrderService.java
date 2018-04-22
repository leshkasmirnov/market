package ru.asmirnov.market.service;

import ru.asmirnov.market.common.PayMethod;
import ru.asmirnov.market.db.entity.PersonCart;
import ru.asmirnov.market.db.entity.PersonOrder;

import java.util.List;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
public interface OrderService {

    PersonOrder createOrder(List<PersonCart> personCarts, String address, PayMethod payMethod);

    void acceptOrder(PersonOrder personOrder);
}
