package com.anychart.controllers.views;

import com.anychart.controllers.panels.ResultStorePanel;
import com.anychart.controllers.window.LoginWindow;
import com.anychart.dao.PersonDAO;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * The mainpanel is just the a defaultPanel
 */
public class MainView extends VerticalLayout implements View {

    SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    PersonDAO personDAO = new PersonDAO();



    public MainView() {
        personDAO.setSessionFactory(sessionFactory);
        setSizeFull();


        Button loginButton = new Button("login");

        loginButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                final LoginWindow dialog = new LoginWindow("Login to existing user");

                ResultStorePanel rp = new ResultStorePanel();
                dialog.setDialogContent(rp);
                dialog.setModal(true);

                UI.getCurrent().addWindow(dialog);
            }
        });

        Button createButton = new Button("Create a new user (only loginname)");

        createButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                final LoginWindow dialog = new LoginWindow("Create new user");

                ResultStorePanel rp = new ResultStorePanel();
                dialog.setDialogContent(rp);
                dialog.setModal(true);


                UI.getCurrent().addWindow(dialog);
                dialog.setListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        UI.getCurrent().removeWindow(dialog);
                        if ("OKBUTTON".equals(event.getButton().getId())) {


                            personDAO.createUser(rp.getUsername(), rp.getPassword());


                            //if (!writeResult) {
                            //    Notification.show("The result can not get stored, please contact support", Notification.Type.ERROR_MESSAGE);
                            //}

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

        this.addComponent(createButton);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome to Main");
    }
}
