package com.anychart.controllers.panels;

import com.vaadin.ui.*;

/**
 * This panel contains results of validations in a titleDelivery, it gives the possiblity of viewing what has been validated
 * and store it if it is approved.
 */
public class UserCreatePanel extends GridLayout {

    private Label usernamelabel = new Label("username");
    private Label passwordlabel = new Label("password");
    private TextField username = new TextField();
    private TextField password = new TextField();

    public UserCreatePanel() {
        super(2,2);
        this.setSpacing(true);

        this.addComponent(usernamelabel,0,0);
        this.addComponent(passwordlabel,0,1);

        this.addComponent(username,1,0);
        this.addComponent(password,1,1);

    }

    public String getUsername() {
        return username.getValue();
    }

    public String getPassword() {
        return password.getValue();
    }


    /**
     * Set values to be viewed in the panel
     * @param item
     */
    public void setValues(Object item) {

    }

    @Override
    public void setEnabled(boolean enabled) {

        super.setEnabled(enabled);
    }
}
