package com.anychart.models;


import com.anychart.models.dao.PersonDAO;
import com.anychart.models.dao.UserDAO;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * DataModel has one initiated instance per browsersession. The datamodel contains cashed information about the
 * deliveries, which is currently beeing checked
 */
public class DataModel {
    protected Logger log = LoggerFactory.getLogger(getClass());

    private SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    private PersonDAO personDAO = new PersonDAO();
    private UserDAO userDAO = new UserDAO();

    public DataModel() {

        personDAO.setSessionFactory(sessionFactory);
        userDAO.setSessionFactory(sessionFactory);

    }

    public void cleanModel() {

    }


    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Transactional
    public void updatePerson(Person p) {

        personDAO.updatePerson(p);

    }

    @Transactional
    public void createPerson(Person p) {
        personDAO.createPerson(p);
    }




}
