package ru.asmirnov.market.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asmirnov.market.db.dao.PersonDao;
import ru.asmirnov.market.db.entity.Person;
import ru.asmirnov.market.service.PersonService;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
@Service
@SuppressWarnings("unused")
public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Person findById(Long id) {
        return personDao.findOne(id);
    }
}
