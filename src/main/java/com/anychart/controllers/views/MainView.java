package com.anychart.controllers.views;

import com.anychart.controllers.panels.PublicComponent;
import com.anychart.dao.UserDAO;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * The mainpanel is just the a defaultPanel
 */
public class MainView extends VerticalLayout implements View {

    SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    UserDAO userDAO = new UserDAO();



    public MainView() {
        userDAO.setSessionFactory(sessionFactory);
        setSizeFull();


        PublicComponent rp = new PublicComponent();
        this.addComponent(rp);


        /*Button loginButton = new Button("login");

        loginButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                final LoginWindow dialog = new LoginWindow("Login to existing user");

                PublicComponent rp = new PublicComponent();
                dialog.setDialogContent(rp);
                dialog.setModal(true);

                UI.getCurrent().addWindow(dialog);
                dialog.setListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        UI.getCurrent().removeWindow(dialog);
                        if ("OKBUTTON".equals(event.getButton().getId())) {
                            String username = rp.getUsername();
                            String password = rp.getPassword();
                            if(username.length() > 3 && password.length() > 7) {
                                String userUuid = userDAO.validateUser(username, rp.getPassword());
                            } else {
                                Notification.show("Invalid username or password", Notification.Type.ERROR_MESSAGE);
                            }
                        }
                    }
                });

                dialog.addCloseListener(new Window.CloseListener() {
                    //This event gets called when the dialog is closed
                    @Override
                    public void windowClose(Window.CloseEvent e) {
                        UI.getCurrent().removeWindow(dialog);
                    }
                });
            }
        });

        Button createButton = new Button("Create a new user");

        createButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                final LoginWindow dialog = new LoginWindow("Create new user (only loginname)");

                PublicComponent rp = new PublicComponent();
                dialog.setDialogContent(rp);
                dialog.setModal(true);




                UI.getCurrent().addWindow(dialog);
                dialog.setListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        UI.getCurrent().removeWindow(dialog);
                        if ("OKBUTTON".equals(event.getButton().getId())) {
                            String username = rp.getUsername();
                            String password = rp.getPassword();

                            AuthService.login(username, password, true);

                            if(username.length() > 3 && password.length() > 7) {
                                String userUuid = userDAO.createUser(username, rp.getPassword());
                                getUI().getNavigator().navigateTo(FamilymapUI.OVERVIEW);
                            } else {
                                Notification.show("Invalid username or password", Notification.Type.ERROR_MESSAGE);
                            }
                        }
                    }
                });

                dialog.addCloseListener(new Window.CloseListener() {
                    //This event gets called when the dialog is closed
                    @Override
                    public void windowClose(Window.CloseEvent e) {
                        UI.getCurrent().removeWindow(dialog);
                    }
                });
            }
        });


        this.addComponent(loginButton);

        this.addComponent(createButton);*/
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome to Main");
    }
}
