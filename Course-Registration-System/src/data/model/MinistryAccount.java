package data.model;

import data.model.base.IBaseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MinistryAccount implements IBaseModel {
    private String ministryId;
    private String username;
    private String password;

    public String getMinistryId() {
        return ministryId;
    }

    public void setMinistryId(String ministryId) {
        this.ministryId = ministryId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MinistryAccount that = (MinistryAccount) o;
        return Objects.equals(ministryId, that.ministryId) && Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ministryId, username, password);
    }

    @Override
    public String toString() {
        return "MinistryAccount{" +
                "ministryId='" + ministryId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public Object[] toRow() {
        final List<Object> rs = new ArrayList<>();
        rs.add(ministryId);
        rs.add(username);
        rs.add(password);
        return rs.toArray();
    }
}
