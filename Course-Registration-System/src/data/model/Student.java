package data.model;

import data.model.base.BaseModel;

import java.sql.Date;
import java.util.Objects;

public class Student extends BaseModel {
    private String studentId;
    private String username;
    private String password;
    private String fullName;
    private Date birthDay;
    private String address;
    private String classId;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId) && Objects.equals(username, student.username) && Objects.equals(password, student.password) && Objects.equals(fullName, student.fullName) && Objects.equals(birthDay, student.birthDay) && Objects.equals(address, student.address) && Objects.equals(classId, student.classId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, username, password, fullName, birthDay, address, classId);
    }

    @Override
    public String getId() {
        return studentId;
    }

    @Override
    public Object[] toRow() {
        return new Object[]{
                studentId,
                username,
                password,
                fullName,
                birthDay,
                address,
                classId,
        };
    }
}
