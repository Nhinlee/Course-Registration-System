package data.dao;

import data.dao.base.IBaseDAO;
import data.model.MinistryAccount;
import data.model.base.IDoJob;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class MinistryAccountDAO implements IBaseDAO<MinistryAccount> {

    @Override
    public List<MinistryAccount> getAll() {
        final List<MinistryAccount> accounts = new ArrayList<>();
        HibernateUtil.openSessionAndDoJob(new IDoJob() {
            @Override
            public void doJob(Session session) {
                String getAllHQL = "from MinistryAccount";
                Query query = session.createQuery(getAllHQL);
                accounts.addAll(query.list());
            }
        });
        return accounts;
    }

    @Override
    public MinistryAccount getById(String id) {
        final MinistryAccount[] account = {null};
        HibernateUtil.openSessionAndDoJob(new IDoJob() {
            @Override
            public void doJob(Session session) {
                account[0] = (MinistryAccount) session.get(MinistryAccount.class, id);
            }
        });
        return account[0];
    }

    @Override
    public boolean insert(MinistryAccount obj) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String insertHQL = "";
            Query query = session.createQuery(insertHQL);
        } catch (HibernateException e) {
            System.err.println(e);
        }
        return false;
    }

    @Override
    public boolean update(MinistryAccount obj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
