package by.specific.service;

import by.specific.model.Purchase;

import java.util.List;

public interface PurchaseService {
    public void addPurchase(Purchase purchase);

    public void updatePurchase(Purchase purchase);

    public void removePurchase(int id);

    public Purchase getPurchaseById(int id);

    public List<Purchase> findPurchases(String name);

    public List<Purchase> listPurchases();
}

