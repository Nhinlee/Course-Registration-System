package data.model.base;

import org.hibernate.Session;

public interface IDoJob {
    void doJob(Session session);
}
