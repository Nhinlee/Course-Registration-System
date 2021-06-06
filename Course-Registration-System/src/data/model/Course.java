package data.model;

import data.model.base.BaseModel;

import java.util.Objects;

public class Course extends BaseModel {
    private String courseId;
    private String courseName;
    private Subject subject;
    private String lecturersFullName;
    private short maxSlot;
    private String roomName;
    private Short dayOfWeek;
    private Short partOfDay;
    private Semester semester;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getLecturersFullName() {
        return lecturersFullName;
    }

    public void setLecturersFullName(String lecturersFullName) {
        this.lecturersFullName = lecturersFullName;
    }

    public short getMaxSlot() {
        return maxSlot;
    }

    public void setMaxSlot(short maxSlot) {
        this.maxSlot = maxSlot;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Short getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Short dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Short getPartOfDay() {
        return partOfDay;
    }

    public void setPartOfDay(Short partOfDay) {
        this.partOfDay = partOfDay;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return maxSlot == course.maxSlot && Objects.equals(courseId, course.courseId) && Objects.equals(courseName, course.courseName) && Objects.equals(subject, course.subject) && Objects.equals(lecturersFullName, course.lecturersFullName) && Objects.equals(roomName, course.roomName) && Objects.equals(dayOfWeek, course.dayOfWeek) && Objects.equals(partOfDay, course.partOfDay) && Objects.equals(semester, course.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, subject, lecturersFullName, maxSlot, roomName, dayOfWeek, partOfDay, semester);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", subjectId='" + subject + '\'' +
                ", lecturersFullName='" + lecturersFullName + '\'' +
                ", maxSlot=" + maxSlot +
                ", roomName='" + roomName + '\'' +
                ", dayOfWeek=" + dayOfWeek +
                ", partOfDay=" + partOfDay +
                ", semester=" + semester +
                '}';
    }

    @Override
    public String getId() {
        return courseId;
    }

    @Override
    public Object[] toRow() {
        return new Object[]{
                courseId,
                courseName,
                subject.getSubjectName(),
                lecturersFullName,
                maxSlot,
                roomName,
                dayOfWeek,
                partOfDay,
                semester.getSemesterName() + " - " + semester.getSchoolYear(),
        };
    }

    public Object[] toStudentViewRow() {
        return new Object[]{
                courseId,
                courseName,
                maxSlot,
                dayOfWeek,
                partOfDay,
        };
    }
}
