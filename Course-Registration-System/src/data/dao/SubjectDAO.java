package data.dao;

import data.dao.base.BaseDAO;
import data.dao.base.IBaseDAO;
import data.model.Subject;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class SubjectDAO extends BaseDAO<Subject> {
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
}
