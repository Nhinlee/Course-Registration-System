package data.model;

import java.util.Objects;

public class Course {
    private String courseId;
    private String courseName;
    private String subjectId;
    private String lecturersFullName;
    private short maxSlot;
    private String roomName;
    private Short dayOfWeek;
    private Short partOfDay;
    private String semesterId;

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

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
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

    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return maxSlot == course.maxSlot && Objects.equals(courseId, course.courseId) && Objects.equals(courseName, course.courseName) && Objects.equals(subjectId, course.subjectId) && Objects.equals(lecturersFullName, course.lecturersFullName) && Objects.equals(roomName, course.roomName) && Objects.equals(dayOfWeek, course.dayOfWeek) && Objects.equals(partOfDay, course.partOfDay) && Objects.equals(semesterId, course.semesterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, subjectId, lecturersFullName, maxSlot, roomName, dayOfWeek, partOfDay, semesterId);
    }
}
