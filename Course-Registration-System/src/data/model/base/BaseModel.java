package data.model.base;

public abstract class BaseModel {
    public abstract String getId();
    public abstract Object[] toRow();
}
