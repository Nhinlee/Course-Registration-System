package data.dao.base;

import data.model.base.BaseModel;
import utils.HibernateUtil;

public abstract class BaseDAO<T extends BaseModel> implements IBaseDAO<T> {

    @Override
    public boolean insert(T obj) {
        final boolean[] result = {true};
        HibernateUtil.openSessionAndDoJob(session -> {
            if (getById(obj.getId()) != null) {
                result[0] = false;
                return;
            }
            result[0] = HibernateUtil.saveToDB(session, obj);
        });
        return result[0];
    }

    @Override
    public boolean update(T obj) {
        final boolean[] result = {true};
        HibernateUtil.openSessionAndDoJob(
                session -> result[0] = HibernateUtil.updateToDB(session, obj)
        );
        return result[0];
    }

    @Override
    public boolean delete(String id) {
        final boolean[] result = {true};
        HibernateUtil.openSessionAndDoJob(session -> {
            // Get by id
            T obj = getById(id);
            if (obj == null) {
                result[0] = false;
                return;
            }
            // Delete
            result[0] = HibernateUtil.deleteInDB(session, obj);
        });
        return result[0];
    }
}
