package app.dao;

public interface DAO<T> {

    void create(T t);

    T retrieve(int id);

    void update(T t);

    void delete(T t);
}
