package service.serviceImpl;

import dao.daoImpl.CompanyDao;
import dao.daoImpl.CompanyDaoImpl;
import entity.Company;
import exception.ShowException;
import org.hibernate.HibernateError;

import java.util.List;

public class CompanyServiceImpl implements CompanyService {

    CompanyDao companyDao = new CompanyDaoImpl();

    public CompanyServiceImpl() {
    }

    @Override
    public boolean addCompany(Company company) {
        boolean isAdded = false;
        try {
            if (companyDao.addCompany(company))
                isAdded = true;
        }
        catch (HibernateError e) {
            ShowException.showNotice(e);
        }
        return isAdded;
    }

    @Override
    public boolean updateCompany(Company company) {
        boolean isUpdated = false;
        try {
            if (companyDao.updateCompany(company))
                isUpdated = true;
        }
        catch (HibernateError e) {
            ShowException.showNotice(e);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteCompany(int id) {
        boolean isDeleted = false;
        try {
            if (companyDao.deleteCompany(id))
                isDeleted = true;
        }
        catch (HibernateError e) {
            ShowException.showNotice(e);
        }
        return isDeleted;
    }

    @Override
    public List<Company> showCompanies() {
        List<Company> companies = null;
        try {
            companies = companyDao.showCompanies();
        }
        catch (HibernateError e) {
            ShowException.showNotice(e);
        }
        return companies;
    }

    @Override
    public Company findCompanyById(int id) {
        Company company = null;
        try {
            company = companyDao.findCompanyById(id);
        }
        catch (HibernateError e) {
            ShowException.showNotice(e);
        }
        return company;
    }

    @Override
    public Company findCompanyByName(String name) {
        Company company = null;
        try {
            company = companyDao.findCompanyByName(name);
        }
        catch (HibernateError e) {
            ShowException.showNotice(e);
        }
        return company;
    }
}
