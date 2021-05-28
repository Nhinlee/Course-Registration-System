package data.dao;

import data.dao.base.IBaseDAO;
import data.model.MinistryAccount;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class MinistryAccountDAO implements IBaseDAO<MinistryAccount> {

    @Override
    public List<MinistryAccount> getAll() {
        final List<MinistryAccount> accounts = new ArrayList<>();
        HibernateUtil.openSessionAndDoJob(session -> {
            String getAllHQL = "from MinistryAccount";
            Query query = session.createQuery(getAllHQL);
            accounts.addAll(query.list());
        });
        return accounts;
    }

    @Override
    public MinistryAccount getById(String id) {
        final MinistryAccount[] account = {null};
        HibernateUtil.openSessionAndDoJob(
                session -> account[0] = session.get(MinistryAccount.class, id)
        );
        return account[0];
    }

    @Override
    public boolean insert(MinistryAccount obj) {
        final boolean[] result = {true};
        HibernateUtil.openSessionAndDoJob(session -> {
            if (getById(obj.getMinistryId()) != null) {
                result[0] = false;
                return;
            }
            result[0] = HibernateUtil.saveToDB(session, obj);
        });
        return result[0];
    }

    @Override
    public boolean update(MinistryAccount obj) {
        final boolean[] result = {true};
        HibernateUtil.openSessionAndDoJob(
                session -> result[0] = HibernateUtil.updateToDB(session, obj)
        );
        return result[0];
    }

    @Override
    public boolean delete(String id) {
        final boolean[] result = {true};
        HibernateUtil.openSessionAndDoJob(session -> {
            // Get by id
            MinistryAccount account = getById(id);
            if (account == null) {
                result[0] = false;
                return;
            }
            // Delete
            result[0] = HibernateUtil.deleteInDB(session, account);
        });
        return result[0];
    }
}
