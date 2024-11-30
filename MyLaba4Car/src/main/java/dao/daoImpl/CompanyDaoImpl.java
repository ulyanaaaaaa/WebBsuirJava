package dao.daoImpl;

import dao.daoImpl.CompanyDao;
import entity.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sessionFactory.SessionFactoryImpl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {

    public CompanyDaoImpl() {}

    @Override
    public boolean addCompany(Company company) {
        boolean isAdded = false;
        try (Session session = SessionFactoryImpl.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(company);
            tx.commit();
            isAdded = true;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return isAdded;
    }

    @Override
    public boolean updateCompany(Company company) {
        boolean isUpdated = false;
        try (Session session = SessionFactoryImpl.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(company);
            tx.commit();
            isUpdated = true;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteCompany(int id) {
        boolean isDeleted = false;
        try (Session session = SessionFactoryImpl.getSessionFactory().openSession()) {
            Company company = session.load(Company.class, id);
            if (company != null) {
                Transaction tx = session.beginTransaction();
                session.delete(company);
                tx.commit();
                isDeleted = true;
            } else {
                System.out.println("Company not found");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return isDeleted;
    }

    @Override
    public Company findCompanyById(int id) {
        Company company = null;
        try (Session session = SessionFactoryImpl.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Company> cr = cb.createQuery(Company.class);
            Root<Company> root = cr.from(Company.class);
            cr.select(root).where(cb.equal(root.get("companyId"), id));
            company = session.createQuery(cr).getSingleResult();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return company;
    }

    @Override
    public Company findCompanyByName(String name) {
        Company company = null;
        try (Session session = SessionFactoryImpl.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Company> cr = cb.createQuery(Company.class);
            Root<Company> root = cr.from(Company.class);
            cr.select(root).where(cb.equal(root.get("companyName"), name));
            company = session.createQuery(cr).getSingleResult();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return company;
    }

    @Override
    public List<Company> showCompanies() {
        List<Company> companies = null;
        try (Session session = SessionFactoryImpl.getSessionFactory().openSession()) {
            companies = session.createQuery("From entity.Company", Company.class).list();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return companies;
    }
}
