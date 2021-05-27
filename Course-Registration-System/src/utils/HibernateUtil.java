/*
package utils;

import org.hibernate.HibernateException;
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
}
*/
