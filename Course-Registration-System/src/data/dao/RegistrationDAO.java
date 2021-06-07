package data.dao;

import data.dao.base.BaseDAO;
import data.model.MinistryAccount;
import data.model.Registration;
import data.model.Semester;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class RegistrationDAO extends BaseDAO<Registration> {
    @Override
    public List<Registration> getAll() {
        final List<Registration> registrations = new ArrayList<>();
        HibernateUtil.openSessionAndDoJob(session -> {
            String getAllHQL = "from Registration ";
            Query query = session.createQuery(getAllHQL);
            registrations.addAll(query.list());
        });
        return registrations;
    }

    @Override
    public Registration getById(String id) {
        final Registration[] registrations = {null};
        HibernateUtil.openSessionAndDoJob(
                session -> registrations[0] = session.get(Registration.class, id)
        );
        return registrations[0];
    }

    public List<Registration> getAllCourseRegisteredByStudentId(String studentId) {
        final List<Registration> registrations = new ArrayList<>();
        HibernateUtil.openSessionAndDoJob(session -> {
            String getAllHQL = String.format("select res from Registration res where res.student.studentId = '%s'", studentId);
            Query query = session.createQuery(getAllHQL);
            registrations.addAll(query.list());
        });
        return registrations;
    }
}
