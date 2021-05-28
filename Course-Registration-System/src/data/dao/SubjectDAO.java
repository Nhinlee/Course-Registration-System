package data.dao;

import data.dao.base.IBaseDAO;
import data.model.Subject;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class SubjectDAO implements IBaseDAO<Subject> {
    @Override
    public List<Subject> getAll() {
        final List<Subject> subjects = new ArrayList<>();
        HibernateUtil.openSessionAndDoJob(session -> {
            String hql = "from Subject";
            Query query = session.createQuery(hql);
            subjects.addAll(query.list());
        });
        return subjects;
    }

    @Override
    public Subject getById(String id) {
        final Subject[] subjects = {null};
        HibernateUtil.openSessionAndDoJob(
                session -> subjects[0] = session.get(Subject.class, id)
        );
        return subjects[0];
    }

    @Override
    public boolean insert(Subject obj) {
        final boolean[] result = {true};
        HibernateUtil.openSessionAndDoJob(session -> {
            if (getById(obj.getSubjectId()) != null) {
                result[0] = false;
                return;
            }
            result[0] = HibernateUtil.saveToDB(session, obj);
        });
        return result[0];
    }

    @Override
    public boolean update(Subject obj) {
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
            Subject subject = getById(id);
            if (subject == null) {
                result[0] = false;
                return;
            }
            // Delete
            result[0] = HibernateUtil.deleteInDB(session, subject);
        });
        return result[0];
    }
}
