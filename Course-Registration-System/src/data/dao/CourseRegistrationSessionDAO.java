package data.dao;

import data.dao.base.BaseDAO;
import data.model.CourseRegistrationSession;
import data.model.Semester;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class CourseRegistrationSessionDAO extends BaseDAO<CourseRegistrationSession> {
    @Override
    public List<CourseRegistrationSession> getAll() {
        final List<CourseRegistrationSession> sessions = new ArrayList<>();
        HibernateUtil.openSessionAndDoJob(session -> {
            String hql = "from CourseRegistrationSession";
            Query query = session.createQuery(hql);
            sessions.addAll(query.list());
        });
        return sessions;
    }

    @Override
    public List<CourseRegistrationSession> getBySearchText(String searchText) {
        return null;
    }

    @Override
    public CourseRegistrationSession getById(String id) {
        final CourseRegistrationSession[] sessions = {null};
        HibernateUtil.openSessionAndDoJob(
                session -> sessions[0] = session.get(CourseRegistrationSession.class, id)
        );
        return sessions[0];
    }
}
