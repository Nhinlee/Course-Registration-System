package data.dao;

import data.dao.base.BaseDAO;
import data.model.Student;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends BaseDAO<Student> {
    @Override
    public List<Student> getAll() {
        final List<Student> students = new ArrayList<>();
        HibernateUtil.openSessionAndDoJob(session -> {
            String getAllHQL = "from Student";
            Query query = session.createQuery(getAllHQL);
            students.addAll(query.list());
        });
        return students;
    }

    @Override
    public Student getById(String id) {
        final Student[] students = {null};
        HibernateUtil.openSessionAndDoJob(
                session -> students[0] = session.get(Student.class, id)
        );
        return students[0];
    }

    public List<Student> getStudentByClassId(String classId) {
        final List<Student> students = new ArrayList<>();
        HibernateUtil.openSessionAndDoJob(session -> {
            String getStudentByClassIdHQL = String.format(
                    "select st from Student st where st.classId = '%s'",
                    classId);
            Query query = session.createQuery(getStudentByClassIdHQL);
            students.addAll(query.list());
        });
        return students;
    }

    public Student login(String username, String password) {
        final List<Student> students = new ArrayList<>();
        HibernateUtil.openSessionAndDoJob(session -> {
            String getStudentByUsernamePasswordHQL = String.format(
                    "select st from Student st where st.username = '%s' and st.password = '%s'",
                    username,
                    password);
            Query query = session.createQuery(getStudentByUsernamePasswordHQL);
            students.addAll(query.list());
        });
        return students.size() > 0 ? students.get(0) : null;
    }
}
