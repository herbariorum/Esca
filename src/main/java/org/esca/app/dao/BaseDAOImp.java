package org.esca.app.dao;

import java.util.List;

public class BaseDAOImp<T, ID> implements BaseDao<T, ID> {

    @Override
    public T save(T entity) {
        return null;
    }

    @Override
    public T getById(ID id) {
        return null;
    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public void update(T entity) {

    }

    @Override
    public List<T> getAll(String query) {
        return null;
    }
}
