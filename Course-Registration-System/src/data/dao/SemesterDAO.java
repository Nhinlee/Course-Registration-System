package data.dao;

import data.dao.base.BaseDAO;
import data.model.Semester;
import org.hibernate.HibernateException;
import org.hibernate.Session;
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

    @Override
    public boolean update(Semester obj) {
        final boolean[] result = {true};
        HibernateUtil.openSessionAndDoJob(
                session -> {
                    if (obj.getIsCurrent() == Semester.IS_CURRENT) {
                        // update old semester
                        Semester currentSemester = getCurrentSemester(session);
                        currentSemester.setIsCurrent(Semester.IS_NOT_CURRENT);
                        HibernateUtil.updateToDB(session, currentSemester);
                    }
                    result[0] = HibernateUtil.updateToDB(session, obj);
                }
        );
        return result[0];
    }

    private Semester getCurrentSemester(Session session) throws HibernateException {
        Semester currentSemester = null;

        String hql = "select s from Semester s where isCurrent = 1";
        Query query = session.createQuery(hql);
        currentSemester = (Semester) query.list().get(0);

        return currentSemester;
    }
}
