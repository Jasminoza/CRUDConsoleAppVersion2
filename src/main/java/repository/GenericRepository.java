package repository;

import java.sql.ResultSet;
import java.util.List;

public interface GenericRepository<T, ID> {
    List<T> getAll();
    T create(T t);
    T getById(ID id);
    T update(T t);
    void delete(ID id);
}
