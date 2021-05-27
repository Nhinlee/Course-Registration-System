package data.dao.base;

import java.util.List;

public interface BaseDAO<T> {
    List<T> getAll();
    T getById();
    boolean insert(T row);
    boolean update(T row);
    boolean delete(String id);
}
