package data.dao;

import data.dao.base.BaseDAO;
import data.model.Clazz;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class ClazzDAO extends BaseDAO<Clazz> {
    @Override
    public List<Clazz> getAll() {
        final List<Clazz> clazzes = new ArrayList<>();
        HibernateUtil.openSessionAndDoJob(session -> {
            String getAllHQL = "from Clazz ";
            Query query = session.createQuery(getAllHQL);
            clazzes.addAll(query.list());
        });
        return clazzes;
    }

    @Override
    public Clazz getById(String id) {
        final Clazz[] clazzes = {null};
        HibernateUtil.openSessionAndDoJob(
                session -> clazzes[0] = session.get(Clazz.class, id)
        );
        return clazzes[0];
    }

    public List<String> getAllClassID() {
        final List<String> classIds = new ArrayList<>();
        HibernateUtil.openSessionAndDoJob(session -> {
            String getAllClassIdHQL = "select c.classId from Clazz c";
            Query query = session.createQuery(getAllClassIdHQL);
            classIds.addAll(query.list());
        });
        return classIds;
    }
}
