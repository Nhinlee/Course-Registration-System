package data.dao;

import data.dao.base.BaseDAO;
import data.model.Semester;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class SemesterDAO extends BaseDAO<Semester> {
    @Override
    public List<Semester> getAll() {
        final List<Semester> semesters = new ArrayList<>();
        HibernateUtil.openSessionAndDoJob(session -> {
            String hql = "from Semester";
            Query query = session.createQuery(hql);
            semesters.addAll(query.list());
        });
        return semesters;
    }

    @Override
    public Semester getById(String id) {
        final Semester[] semesters = {null};
        HibernateUtil.openSessionAndDoJob(
                session -> semesters[0] = session.get(Semester.class, id)
        );
        return semesters[0];
    }
}
