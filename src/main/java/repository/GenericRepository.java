package repository;

import java.sql.ResultSet;

public interface GenericRepository<T, ID> {
    ResultSet getAll();
    ResultSet create(T t);
    ResultSet getById(ID id);
    T update(T t);
    void delete(ID id);
}
