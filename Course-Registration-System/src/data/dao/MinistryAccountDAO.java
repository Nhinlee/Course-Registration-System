package data.dao;

import data.dao.base.BaseDAO;
import data.model.MinistryAccount;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class MinistryAccountDAO extends BaseDAO<MinistryAccount> {

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
    public List<MinistryAccount> getBySearchText(String textSearch) {
        final List<MinistryAccount> accounts = new ArrayList<>();
        HibernateUtil.openSessionAndDoJob(session -> {
            String getAllHQL = "select ac from MinistryAccount ac where ac.username like '%" + textSearch + "%'";
            Query query = session.createQuery(getAllHQL);
            accounts.addAll(query.list());
        });
        return accounts;
    }

    public boolean login(String username, String password) {
        final List<MinistryAccount> accounts = new ArrayList<>();
        HibernateUtil.openSessionAndDoJob(session -> {
            String getAllHQL = String.format(
                    "select mi from MinistryAccount mi where mi.username = '%s' and mi.password = '%s'",
                    username,
                    password);
            Query query = session.createQuery(getAllHQL);
            accounts.addAll(query.list());
        });
        return accounts.size() > 0;
    }
}
