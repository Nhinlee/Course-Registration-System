package utils;

import data.model.base.IDoJob;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration config = new Configuration();
            config.configure();

            sessionFactory = config.buildSessionFactory();
        } catch (HibernateException e) {
            System.err.println(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void openSessionAndDoJob(IDoJob job) {
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            job.doJob(session);
        } catch (HibernateException e) {
            System.err.println(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
    }
}
