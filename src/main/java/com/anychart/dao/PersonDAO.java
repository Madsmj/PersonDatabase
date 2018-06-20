package com.anychart.dao;

import com.anychart.models.Person;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class PersonDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Person> getPersons(int count) {
        sessionFactory.getCurrentSession().beginTransaction();
        return sessionFactory.getCurrentSession().createCriteria(Person.class).addOrder(Order.desc("value")).setMaxResults(count).list();
    }



    @Transactional
    public void createPerson(String firstName, String middleName, String lastName) {

        sessionFactory.getCurrentSession().beginTransaction();

        Person p = new Person();
        p.setFirstname(firstName);
        p.setMiddlename(middleName);
        p.setLastname(lastName);

        sessionFactory.getCurrentSession().save(p);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Transactional
    public String createPerson(Person p) {

        sessionFactory.getCurrentSession().beginTransaction();
        sessionFactory.getCurrentSession().save(p);
        sessionFactory.getCurrentSession().getTransaction().commit();
        return p.getUuid();
    }

    @Transactional
    public List<Person> findPerson(String firstname, String middlename, String lastname, String uuid) {

        sessionFactory.getCurrentSession().beginTransaction();
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Person.class);

        if(firstname!=null) {
            criteria = criteria.add(Restrictions.eq("firstname", firstname));
        }
        if(middlename!=null) {
            criteria = criteria.add(Restrictions.eq("middlename", middlename));
        }
        if(lastname!=null) {
            criteria = criteria.add(Restrictions.eq("lastname", lastname));
        }
        if(uuid!=null) {
            criteria = criteria.add(Restrictions.eq("uuid", uuid));
        }
        List<Person> list = criteria.list();
        sessionFactory.getCurrentSession().getTransaction().rollback();
        return list;

    }

    @Transactional
    public void updatePerson(Person p) {

        Transaction tx = sessionFactory.getCurrentSession().beginTransaction();
        sessionFactory.getCurrentSession().update(p);
        tx.commit();

    }


}
