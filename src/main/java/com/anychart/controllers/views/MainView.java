package com.anychart.controllers.views;

import com.anychart.controllers.NewspaperUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.addon.oauthpopup.OAuthListener;
import org.vaadin.addon.oauthpopup.buttons.FacebookButton;
import org.vaadin.addon.oauthpopup.buttons.GooglePlusButton;


/**
 * The mainpanel is just the a defaultPanel
 */
public class MainView extends VerticalLayout implements View {
    public MainView() {
        setSizeFull();


        //GooglePlusButton button = new GooglePlusButton("12894100090-tqso3lih5o42isneort886la2pesafmp.apps.googleusercontent.com","9xfU16efvxQ-BTMsXT9wOLpw");
        //button.setScope("profile email");

        FacebookButton button = new FacebookButton("12894100090-tqso3lih5o42isneort886la2pesafmp.apps.googleusercontent.com","9xfU16efvxQ-BTMsXT9wOLpw");
        addComponent(button);
        setComponentAlignment(button, Alignment.MIDDLE_CENTER);





        button.addOAuthListener(new OAuthListener() {
            @Override
            public void authSuccessful(String s, String s1, String s2) {
                System.out.println(1);
            }

            @Override
            public void authDenied(String s) {
                System.out.println(1);
            }
        });

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome to Main");
    }
}
