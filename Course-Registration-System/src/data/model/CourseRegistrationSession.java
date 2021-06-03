package data.model;

import data.model.base.BaseModel;

import java.sql.Date;
import java.util.Objects;

public class CourseRegistrationSession extends BaseModel {
    private String sessionId;
    private Date startDate;
    private Date endDate;
    private String semesterId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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
        CourseRegistrationSession that = (CourseRegistrationSession) o;
        return Objects.equals(sessionId, that.sessionId) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(semesterId, that.semesterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, startDate, endDate, semesterId);
    }

    @Override
    public String getId() {
        return sessionId;
    }

    @Override
    public Object[] toRow() {
        return new String[]{sessionId, startDate.toString(), endDate.toString(), semesterId,};
    }
}
