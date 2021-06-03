package data.model;

import data.model.base.BaseModel;

import java.util.Objects;

public class Clazz extends BaseModel {
    private String classId;
    private short maleStudents;
    private short femaleStudents;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public short getMaleStudents() {
        return maleStudents;
    }

    public void setMaleStudents(short maleStudents) {
        this.maleStudents = maleStudents;
    }

    public short getFemaleStudents() {
        return femaleStudents;
    }

    public void setFemaleStudents(short femaleStudents) {
        this.femaleStudents = femaleStudents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clazz clazz = (Clazz) o;
        return maleStudents == clazz.maleStudents && femaleStudents == clazz.femaleStudents && Objects.equals(classId, clazz.classId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId, maleStudents, femaleStudents);
    }

    @Override
    public String getId() {
        return classId;
    }

    @Override
    public Object[] toRow() {
        return new Object[]{classId, maleStudents, femaleStudents,};
    }
}
