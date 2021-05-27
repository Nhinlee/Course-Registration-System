package data.model;

import java.sql.Date;
import java.util.Objects;

public class Semester {
    private String semesterId;
    private String semesterName;
    private String schoolYear;
    private Date startDate;
    private Date endDate;
    //private Boolean isCurrent;

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

    /*public Boolean getCurrent() {
        return isCurrent;
    }

    public void setCurrent(Boolean current) {
        isCurrent = current;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return Objects.equals(semesterId, semester.semesterId)
                && Objects.equals(semesterName, semester.semesterName)
                && Objects.equals(schoolYear, semester.schoolYear)
                && Objects.equals(startDate, semester.startDate)
                && Objects.equals(endDate, semester.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(semesterId, semesterName, schoolYear, startDate, endDate);
    }
}
