package data.dao;

import data.dao.base.IBaseDAO;
import data.model.Subject;
import data.model.base.IDoJob;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class SubjectDAO implements IBaseDAO<Subject> {
    @Override
    public List<Subject> getAll() {
        final List<Subject> subjects = new ArrayList<>();
        HibernateUtil.openSessionAndDoJob(new IDoJob() {
            @Override
            public void doJob(Session session) {
                String hql = "from Subject";
                Query query = session.createQuery(hql);
                subjects.addAll(query.list());
            }
        });
        return subjects;
    }

    @Override
    public Subject getById(String id) {
        return null;
    }

    @Override
    public boolean insert(Subject obj) {
        return false;
    }

    @Override
    public boolean update(Subject obj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
