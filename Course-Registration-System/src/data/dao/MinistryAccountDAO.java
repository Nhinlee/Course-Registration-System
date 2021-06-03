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
}
