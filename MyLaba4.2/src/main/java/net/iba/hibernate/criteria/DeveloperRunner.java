package net.iba.hibernate.criteria;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;

import java.util.List;
import java.util.Map;

public class DeveloperRunner {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        DeveloperRunner developerRunner = new DeveloperRunner();

        System.out.println("Adding developer's records to the database...");
        Integer developerId1 = developerRunner.addDeveloper("Ihor", "Developer", "Java Developer", 3, 2000);
        Integer developerId2 = developerRunner.addDeveloper("First", "Developer", "C++ Developer", 10, 5000);
        Integer developerId3 = developerRunner.addDeveloper("Second", "Developer", "C# Developer", 5, 4000);
        Integer developerId4 = developerRunner.addDeveloper("Third", "Developer", "PHP Developer", 1, 1000);

        System.out.println("List of Developers using Entity Query:");
        developerRunner.listDevelopers();

        System.out.println("List of Developers using Scalar Query:");
        developerRunner.listDevelopersScalar();
        sessionFactory.close();
    }

    public Integer addDeveloper(String firstName, String lastName, String specialty, int experience, int salary) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Integer developerId = null;

        transaction = session.beginTransaction();
        Developer developer = new Developer(firstName, lastName, specialty, experience, salary);
        developerId = (Integer) session.save(developer);
        transaction.commit();
        session.close();
        return developerId;
    }

    public void listDevelopers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM HIBERNATE_DEVELOPERS");
        sqlQuery.addEntity(Developer.class);
        List<Developer> developers = sqlQuery.list();

        for (Developer developer : developers) {
            System.out.println("=======================");
            System.out.println(developer);
            System.out.println("=======================");
        }
        transaction.commit();
        session.close();
    }

    public void listDevelopersScalar() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM HIBERNATE_DEVELOPERS");
        sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Developer> developers = sqlQuery.list();
        for (Object developer : developers) {
            Map row = (Map) developer;
            System.out.println("=======================");
            System.out.println("id: " + row.get("id"));
            System.out.println("First Name: " + row.get("FIRST_NAME"));
            System.out.println("Last Name: " + row.get("LAST_NAME"));
            System.out.println("Specialty: " + row.get("SPECIALTY"));
            System.out.println("Experience: " + row.get("EXPERIENCE"));
            System.out.println("Salary: " + row.get("SALARY"));
            System.out.println("=======================");
        }
        transaction.commit();
        session.close();
    }

    public void totalSalary() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Developer.class);
        criteria.setProjection(Projections.sum("salary"));

        List totalSalary = criteria.list();
        System.out.println("Total salary of all developers: " + totalSalary.get(0));
        transaction.commit();
        session.close();
    }


}


