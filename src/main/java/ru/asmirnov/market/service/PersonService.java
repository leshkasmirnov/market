package ru.asmirnov.market.service;

import ru.asmirnov.market.db.entity.Person;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
public interface PersonService {

    Person findById(Long id);
}
