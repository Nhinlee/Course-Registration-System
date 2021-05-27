/*
package data.dao;

import data.dao.base.BaseDAO;
import data.model.MinistryAccount;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;

public class MinistryAccountDAO implements BaseDAO<MinistryAccount> {

    @Override
    public List<MinistryAccount> getAll() {
        List<MinistryAccount> accounts = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String getAllHQL = "from MinistryAccount";
            Query query = session.createQuery(getAllHQL);
            accounts = query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        }
        return accounts;
    }

    @Override
    public MinistryAccount getById() {
        return null;
    }

    @Override
    public boolean insert(MinistryAccount row) {
        return false;
    }

    @Override
    public boolean update(MinistryAccount row) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
*/
