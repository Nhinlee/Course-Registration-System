package data.model;

import data.model.base.BaseModel;

import java.util.Objects;

public class Registration extends BaseModel {
    private String registrationId;
    private Course course;
    private Student student;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registration that = (Registration) o;
        return Objects.equals(registrationId, that.registrationId) && Objects.equals(course, that.course) && Objects.equals(student, that.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationId, course, student);
    }

    @Override
    public String getId() {
        return registrationId;
    }

    @Override
    public Object[] toRow() {
        return new Object[]{course.getCourseId(), student.getStudentId()};
    }

    @Override
    public String toString() {
        return "Registration{" +
                "registrationId='" + registrationId + '\'' +
                ", course=" + course +
                ", student=" + student +
                '}';
    }
}
