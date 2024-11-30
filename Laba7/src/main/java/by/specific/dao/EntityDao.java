package by.specific.dao;

import org.hibernate.SessionFactory;

import java.util.List;

public abstract class EntityDao<T> {

    protected SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public abstract void add(T entity);
    public abstract void update(T entity);
    public abstract void remove(int id);
    public abstract T getById(int id);
    public abstract List<T> getList();
}
