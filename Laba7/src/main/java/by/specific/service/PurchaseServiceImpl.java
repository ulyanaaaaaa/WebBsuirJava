package by.specific.service;

import by.specific.model.Purchase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import by.specific.dao.PurchaseDaoImpl;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseDaoImpl purchaseDao;

    public void setPurchaseDao(PurchaseDaoImpl purchaseDao) {
        this.purchaseDao = purchaseDao;
    }

    @Override
    @Transactional
    public void addPurchase(Purchase purchase) {
        this.purchaseDao.add(purchase);
    }

    @Override
    @Transactional
    public void updatePurchase(Purchase purchase) {
        this.purchaseDao.update(purchase);
    }

    @Override
    @Transactional
    public void removePurchase(int id) {
        this.purchaseDao.remove(id);
    }

    @Override
    @Transactional
    public Purchase getPurchaseById(int id) {
        return this.purchaseDao.getById(id);
    }

    @Override
    @Transactional
    public List<Purchase> findPurchases(String name) {
        List list = new ArrayList();
        for (Purchase purch:this.purchaseDao.getList()) {
            if(purch.getName().equals(name))
                list.add(purch);
        }
        return list;
    }

    @Override
    @Transactional
    public List<Purchase> listPurchases() {
        return this.purchaseDao.getList();
    }
}
