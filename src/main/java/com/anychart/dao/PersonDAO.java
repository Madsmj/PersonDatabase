package com.anychart.dao;

import com.anychart.models.Person;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
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
    public List<Person> getFruits(int count) {

        sessionFactory.getCurrentSession().beginTransaction();



        return sessionFactory.getCurrentSession().createCriteria(Person.class).addOrder(Order.desc("value")).setMaxResults(count).list();
    }

    @Transactional
    public void createPerson(int count) {

        sessionFactory.getCurrentSession().beginTransaction();

        Person p = new Person();
        p.setId(3);
        p.setName("m");
        p.setValue(4);




        sessionFactory.getCurrentSession().save(p);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

}
