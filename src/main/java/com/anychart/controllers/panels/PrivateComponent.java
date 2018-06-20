package com.anychart.controllers.panels;

import com.anychart.controllers.utils.AuthService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class PrivateComponent extends CustomComponent {

    public PrivateComponent() {
        String username = (String) VaadinSession.getCurrent()
                .getAttribute(AuthService.SESSION_USERNAME);

        Label label = new Label("Welcome, " + username);


        Button button = new Button("Sign out", this::onLogout);

        setCompositionRoot(new VerticalLayout(label, button));
    }

    private void onLogout(Button.ClickEvent event) {
        AuthService.logOut();
    }
}