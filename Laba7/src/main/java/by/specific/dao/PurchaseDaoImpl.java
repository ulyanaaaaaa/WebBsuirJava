package by.specific.dao;

import by.specific.model.Purchase;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by purchase1 on 6/23/2017.
 */
@Repository
public class PurchaseDaoImpl extends EntityDao<Purchase> {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseDaoImpl.class);

    @Override
    public void add(Purchase entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(entity);
        logger.info("Purchase successfully saved. Purchase details: " + entity);
    }

    @Override
    public void update(Purchase entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(entity);
        logger.info("Purchase successfully updated. Purchase details: " + entity);
    }

    @Override
    public void remove(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Purchase purchase = (Purchase) session.load(Purchase.class, Integer.valueOf(id));
        if (purchase != null) {
            session.delete(purchase);
        }
        logger.info("Purchase successfully removed. Purchase details: " + purchase);
    }

    @Override
    public Purchase getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Purchase purchase = (Purchase) session.load(Purchase.class, Integer.valueOf(id));
        logger.info("Purchase successfully loaded. Purchase details: " + purchase);
        return purchase;
    }

    @Override
    public List<Purchase> getList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Purchase> purchaseList = session.createQuery("from Purchase").list();
        for (Purchase purchase : purchaseList) {
            logger.info("Purchase list: " + purchase);
        }
        return purchaseList;
    }
}
