package data.model;

import data.model.base.BaseModel;

import java.sql.Date;
import java.util.Objects;

public class Semester extends BaseModel {
    public static final short IS_CURRENT = 1;
    public static final short IS_NOT_CURRENT = 0;

    private String semesterId;
    private String semesterName;
    private String schoolYear;
    private Date startDate;
    private Date endDate;
    private short isCurrent;

    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public short getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(short isCurrent) {
        this.isCurrent = isCurrent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return isCurrent == semester.isCurrent && Objects.equals(semesterId, semester.semesterId) && Objects.equals(semesterName, semester.semesterName) && Objects.equals(schoolYear, semester.schoolYear) && Objects.equals(startDate, semester.startDate) && Objects.equals(endDate, semester.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(semesterId, semesterName, schoolYear, startDate, endDate, isCurrent);
    }

    @Override
    public String getId() {
        return semesterId;
    }

    @Override
    public Object[] toRow() {
        return new Object[]{semesterId, semesterName, schoolYear, startDate, endDate, isCurrent,};
    }
}
