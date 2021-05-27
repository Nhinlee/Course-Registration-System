package data.dao.base;

import java.util.List;

public interface IBaseDAO<T> {
    List<T> getAll();
    T getById(String id);
    boolean insert(T obj);
    boolean update(T obj);
    boolean delete(String id);
}
