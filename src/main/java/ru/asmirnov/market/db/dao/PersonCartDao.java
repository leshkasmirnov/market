package ru.asmirnov.market.db.dao;

import ru.asmirnov.market.db.dao.common.CrudOperations;
import ru.asmirnov.market.db.entity.Person;
import ru.asmirnov.market.db.entity.PersonCart;

import java.util.List;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
public interface PersonCartDao extends CrudOperations<PersonCart> {

    List<PersonCart> findByPerson(Person person);

    void deleteByPerson(Person person);
}
