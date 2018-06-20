package com.anychart.controllers.panels;

import com.anychart.controllers.utils.AuthService;
import com.anychart.dao.UserDAO;
import com.anychart.models.Person;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class PrivateComponent extends CustomComponent {



    public PrivateComponent() {

        MenuBar header = new MenuBar();
        header.setWidth("100%");

        String username = (String) VaadinSession.getCurrent()
                .getAttribute(AuthService.SESSION_USERNAME);

        Label label = new Label("Welcome, " + username);

        MenuBar.Command searchCommand = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                SearchPanel sp = new SearchPanel();
                setCompositionRoot(new VerticalLayout(header, label, sp));
            }
        };
        header.addItem("search", searchCommand);


        MenuBar.Command signoutCommand = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                AuthService.logOut();
            }
        };
        header.addItem("signout", signoutCommand);






        // Have some data
        List<Person> people = Arrays.asList(
                new Person("Nicolaus Copernicus", "","",""),
                new Person("Galileo Galilei", "","",""),
                new Person("Johannes Kepler", "","",""));

// Create a grid bound to the list
        Grid<Person> grid = new Grid<>();
        grid.setItems(people);
        grid.addColumn(Person::getFirstname).setCaption("Firstname");
        grid.addColumn(Person::getLastname).setCaption("Lastname");



        VerticalLayout vl = new VerticalLayout();
        vl.addComponent(grid);
        setCompositionRoot(new VerticalLayout(header, label, grid));
    }

    private void onLogout(Button.ClickEvent event) {
        AuthService.logOut();
    }
}