package com.anychart.controllers.views;

import com.anychart.controllers.NewspaperUI;
import com.anychart.controllers.panels.ResultStorePanel;
import com.anychart.controllers.window.LoginWindow;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.vaadin.addon.oauthpopup.OAuthListener;
import org.vaadin.addon.oauthpopup.buttons.FacebookButton;
import org.vaadin.addon.oauthpopup.buttons.GooglePlusButton;


/**
 * The mainpanel is just the a defaultPanel
 */
public class MainView extends VerticalLayout implements View {
    public MainView() {
        setSizeFull();


        Button loginButton = new Button("login");

        loginButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                final LoginWindow dialog = new LoginWindow(" - ");

                ResultStorePanel rp = new ResultStorePanel();
                dialog.setDialogContent(rp);
                dialog.setModal(true);

                UI.getCurrent().addWindow(dialog);
            }
        });

        Button createButton = new Button("Create user");

        createButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                final LoginWindow dialog = new LoginWindow(" - ");

                ResultStorePanel rp = new ResultStorePanel();
                dialog.setDialogContent(rp);
                dialog.setModal(true);

                UI.getCurrent().addWindow(dialog);
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
