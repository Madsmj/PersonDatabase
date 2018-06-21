package com.anychart.models.dao;

import com.anychart.models.Person;
import com.anychart.models.User;
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
public class UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<User> getUsers(int count) {
        sessionFactory.getCurrentSession().beginTransaction();
        return sessionFactory.getCurrentSession().createCriteria(User.class).addOrder(Order.desc("value")).setMaxResults(count).list();
    }



    @Transactional
    public String createUser(String username, String password) {
        sessionFactory.getCurrentSession().beginTransaction();
        User p = new User();
        p.setUsername(username);
        p.setPassword(password);
        sessionFactory.getCurrentSession().save(p);
        sessionFactory.getCurrentSession().getTransaction().commit();
        return p.getUuid();
    }

    @Transactional
    public User validateUser(String username, String password) {

        sessionFactory.getCurrentSession().beginTransaction();
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria = criteria.add(Restrictions.eq("username", username));
        List<User> list = criteria.list();
        sessionFactory.getCurrentSession().getTransaction().rollback();
        if(password.equals(list.get(0).getPassword())) {
            return list.get(0);
        }
        return null;
    }

    @Transactional
    public void createUser(String firstName, String middleName, String lastName) {

        sessionFactory.getCurrentSession().beginTransaction();

        User p = new User();
        /*p.setFirstname(firstName);
        p.setMiddlename(middleName);
        p.setLastname(lastName);*/

        sessionFactory.getCurrentSession().save(p);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Transactional
    public String createUser(User p) {

        sessionFactory.getCurrentSession().beginTransaction();
        sessionFactory.getCurrentSession().save(p);
        sessionFactory.getCurrentSession().getTransaction().commit();
        return p.getUuid();
    }

    @Transactional
    public void updateUser(User u) {

        Transaction tx = sessionFactory.getCurrentSession().beginTransaction();
        sessionFactory.getCurrentSession().update(u);
        tx.commit();

    }



}
