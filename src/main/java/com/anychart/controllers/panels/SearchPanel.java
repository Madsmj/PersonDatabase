package com.anychart.controllers.panels;


import com.anychart.dao.PersonDAO;
import com.anychart.dao.UserDAO;
import com.anychart.models.Person;
import com.vaadin.ui.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Panel for handling searching of values for checkings
 */
public class SearchPanel extends VerticalLayout {

    SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    PersonDAO personDAO = new PersonDAO();

    HorizontalLayout hz = new HorizontalLayout();

    Button searchButton = new Button("Search");

    private DateField startDf = new DateField("Lived", LocalDate.now());

    TextField firstname = new TextField("Firstname");
    TextField middlename = new TextField("MiddleName(s)");
    TextField lastname = new TextField("Lastname");
    List<Person> people;
    Grid<Person> grid = new Grid<>();


    public SearchPanel() {

        personDAO.setSessionFactory(sessionFactory);

        searchButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                people = personDAO.findPerson(firstname.getValue(),firstname.getValue(),firstname.getValue(), null);
                grid.setItems(people);
            }
        });


        // Have some data
        /*List<Person> people = Arrays.asList(
                new Person("Nicolaus Copernicus", "a","",""),
                new Person("Galileo Galilei", "v","",""),
                new Person("Johannes Kepler", "s","",""));*/

// Create a grid bound to the list


        grid.addColumn(Person::getFirstname).setCaption("Firstname");
        grid.addColumn(Person::getLastname).setCaption("Lastname");

        hz.addComponent(searchButton);
        hz.addComponent(firstname);
        hz.addComponent(middlename);
        hz.addComponent(lastname);
        hz.addComponent(startDf);

        this.addComponent(hz);
        this.addComponent(grid);
    }


    /**
     * Add listeners to buttons in panel
     * @param listener
     */
    public void addClickListener(Button.ClickListener listener) {

    }
}
