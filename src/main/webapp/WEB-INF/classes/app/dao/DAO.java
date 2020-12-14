package app.dao;

import app.dao.exception.DAOException;

public interface DAO<T> {

    void create(T t) throws DAOException;

    T retrieve(int id) throws DAOException;

    void update(T t) throws DAOException;

    void delete(int id) throws DAOException;
}
