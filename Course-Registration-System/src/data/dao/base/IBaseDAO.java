package data.dao.base;

import data.model.base.BaseModel;

import java.util.List;

public interface IBaseDAO<T extends BaseModel> {
    List<T> getAll();
    List<T> getBySearchText(String searchText);
    T getById(String id);
    boolean insert(T obj);
    boolean update(T obj);
    boolean delete(String id);
}
