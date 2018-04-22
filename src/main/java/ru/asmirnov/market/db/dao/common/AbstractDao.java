package ru.asmirnov.market.db.dao.common;

import java.io.Serializable;

/**
 * @author Alexey Smirnov at 22/04/2018
 */
public abstract class AbstractDao<T extends Serializable> {

    protected Class<T> entityClass;

    protected final void setEntityClass(final Class<T> entityClass) {
        this.entityClass = entityClass;
    }
}
