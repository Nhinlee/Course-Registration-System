package data.dao;

import data.dao.base.BaseDAO;
import data.model.Course;
import data.model.Registration;
import data.model.Semester;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class CourseDAO extends BaseDAO<Course> {

    @Override
    public List<Course> getAll() {
        final List<Course> courses = new ArrayList<>();
        HibernateUtil.openSessionAndDoJob(session -> {

            String getAllHQL = "from Course";
            Query query = session.createQuery(getAllHQL);
            courses.addAll(query.list());
        });
        return courses;
    }

    @Override
    public Course getById(String id) {
        final Course[] courses = {null};
        HibernateUtil.openSessionAndDoJob(
                session -> courses[0] = session.get(Course.class, id)
        );
        return courses[0];
    }

    public List<Course> getAllCourseOfCurrentSemester() {
        // Get Current Semester
        final SemesterDAO semesterDAO = new SemesterDAO();
        final Semester currentSemester = semesterDAO.getCurrentSemester();
        //
        final List<Course> courses = new ArrayList<>();
        HibernateUtil.openSessionAndDoJob(session -> {
            String getAllHQL = String.format("select c from Course c where c.semester.semesterId = '%s'", currentSemester.getSemesterId());
            Query query = session.createQuery(getAllHQL);
            courses.addAll(query.list());
        });
        return courses;
    }

    public List<Course> getAllCourseRemainByStudentId(String studentId) {
        List<Course> rs = new ArrayList<>();
        //
        final RegistrationDAO registrationDAO = new RegistrationDAO();
        final List<Registration> registrations = registrationDAO.getAllCourseRegisteredByStudentId(studentId);
        //
        final List<Course> courses = getAllCourseOfCurrentSemester();
        for (Course course : courses) {
            boolean isRegistered = false;
            for (Registration res : registrations) {
                if (res.getCourse().getCourseId().equals(course.getCourseId())) {
                    isRegistered = true;
                    break;
                }
            }
            if (!isRegistered) {
                rs.add(course);
            }
        }
        //
        return rs;
    }

}
