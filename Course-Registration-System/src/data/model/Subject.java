package data.model;

import java.util.Objects;

public class Subject {
    private String subjectId;
    private String subjectName;
    private short numberOfCredits;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public short getNumberOfCredits() {
        return numberOfCredits;
    }

    public void setNumberOfCredits(short numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return numberOfCredits == subject.numberOfCredits && Objects.equals(subjectId, subject.subjectId) && Objects.equals(subjectName, subject.subjectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectId, subjectName, numberOfCredits);
    }
}
