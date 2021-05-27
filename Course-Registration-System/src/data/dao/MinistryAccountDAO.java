package data.dao;

import data.dao.base.IBaseDAO;
import data.model.MinistryAccount;
import data.model.base.IDoJob;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
                account[0] = session.get(MinistryAccount.class, id);
            }
        });
        return account[0];
    }

    @Override
    public boolean insert(MinistryAccount obj) {
        final boolean[] result = {true};
        HibernateUtil.openSessionAndDoJob(new IDoJob() {
            @Override
            public void doJob(Session session) {
                if (getById(obj.getMinistryId()) != null) {
                    result[0] = false;
                    return;
                }
                Transaction transaction = null;
                try {
                    transaction = session.beginTransaction();
                    session.save(obj);
                    transaction.commit();
                } catch (HibernateException e) {
                    assert transaction != null;
                    transaction.rollback();
                    System.err.println(e);
                    result[0] = false;
                }
            }
        });
        return result[0];
    }

    @Override
    public boolean update(MinistryAccount obj) {
        final boolean[] result = {true};
        HibernateUtil.openSessionAndDoJob(new IDoJob() {
            @Override
            public void doJob(Session session) {
                Transaction transaction = null;
                try {
                    transaction = session.beginTransaction();
                    session.update(obj);
                    transaction.commit();
                } catch (HibernateException e) {
                    assert transaction != null;
                    transaction.rollback();
                    System.err.println(e);
                    result[0] = false;
                }
            }
        });
        return result[0];
    }

    @Override
    public boolean delete(String id) {
        final boolean[] result = {true};
        HibernateUtil.openSessionAndDoJob(new IDoJob() {
            @Override
            public void doJob(Session session) {
                Transaction transaction = null;
                try {
                    // Get by id
                    MinistryAccount account = getById(id);
                    if (account == null) {
                        result[0] = false;
                        return;
                    }

                    // Delete
                    transaction = session.beginTransaction();
                    session.delete(account);
                    transaction.commit();
                } catch (HibernateException e) {
                    assert transaction != null;
                    transaction.rollback();
                    System.err.println(e);
                    result[0] = false;
                }
            }
        });
        return result[0];
    }
}
