package com.anychart.controllers.panels;

import com.anychart.controllers.utils.AuthService;
import com.anychart.models.Person;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

import java.util.Arrays;
import java.util.List;

public class PrivateComponent extends CustomComponent {

    String uuid;


    public PrivateComponent(String uuid) {

        this.uuid = uuid;

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

        MenuBar.Command connectionDiagramCommand = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                ConnectionPanel sp = new ConnectionPanel();
                setCompositionRoot(new VerticalLayout(header, label, sp));
            }
        };
        header.addItem("Connections", connectionDiagramCommand);

        MenuBar.Command treeDiagramCommand = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                TreePanel sp = new TreePanel();
                setCompositionRoot(new VerticalLayout(header, label, sp));
            }
        };
        header.addItem("Tree", treeDiagramCommand);

        MenuBar.Command personCreateCommand = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                PersonCreatePagePanel sp = new PersonCreatePagePanel();
                setCompositionRoot(new VerticalLayout(header, label, sp));
            }
        };
        header.addItem("create person", personCreateCommand);



        MenuBar.Command signoutCommand = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                AuthService.logOut();
            }
        };
        header.addItem("signout", signoutCommand);


        // Have some data
        List<Person> people = Arrays.asList(
                new Person("", "","",""),
                new Person("", "","",""),
                new Person("", "","",""));

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