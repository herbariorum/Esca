package org.esca.app.dao;

import java.util.List;

public interface BaseDao<T, ID> {
    public abstract T save(T entity);
    public abstract T getById(ID id);
    public abstract void delete(T entity);
    public abstract void update(T entity);
    public abstract List<T> getAll(String query);
}
